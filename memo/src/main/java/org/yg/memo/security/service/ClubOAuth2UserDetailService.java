package org.yg.memo.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.yg.memo.entity.ClubMember;
import org.yg.memo.entity.ClubMemberRole;
import org.yg.memo.repository.ClubMemberRepository;
import org.yg.memo.security.dto.ClubAuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuth2UserDetailService extends DefaultOAuth2UserService {

    private final ClubMemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        log.info("-----------oauth USER INFO START------------------");
        log.info("userRequest: " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName : " + clientName);
        //log.info("Client ID" + userRequest.getClientRegistration().getClientId());
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User= super.loadUser(userRequest);
        log.info("======================");
        oAuth2User.getAttributes().forEach((k, v) ->{
            log.info(k + ": " + v);
        });
        log.info("-----------oauth USER INFO END------------------");

        String email = null;
        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
        }

        log.info("EMAIL OF " + clientName + " : " + email);
//        ClubMember member = saveSocialMember(email);
//
//        return oAuth2User;
        ClubMember member = saveSocialMember(email);

        ClubAuthMemberDTO clubAuthMemberDTO = new ClubAuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                        role-> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        clubAuthMemberDTO.setName(member.getName());

        return clubAuthMemberDTO;
    }

    // Socail 로그인시 당연히 Resource Server 의 password 정보는 알수 없다.
    // 그래서 임으로 세팅하고 Login 처리시 Socail 로 Registration 한 User는 Form 로그인을 사용할 수 없게 해야 한다.
    private ClubMember saveSocialMember(String email) {
        Optional<ClubMember> result = repository.findByEmail(email, true);

        if (result.isPresent()){
            log.info("Already Registered ");
            return result.get();
        }

        ClubMember clubMember = ClubMember.builder()
                .email(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();
        clubMember.addMemberRole(ClubMemberRole.USER);
        repository.save(clubMember);
        return clubMember;
    }

}
