package org.yg.practice.security.configures.provider;

import lombok.extern.slf4j.Slf4j;
import org.yg.practice.security.data.OpenStackAuth;
import org.yg.practice.security.data.UserInformation;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.openstack.identity.v3.domain.KeystoneProject;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OpenStackTokenAuthProvider implements AuthenticationProvider {

    private final OSClient.OSClientV3 adminOsClient;

    public OpenStackTokenAuthProvider(OSClient.OSClientV3 adminOsClient) {
        this.adminOsClient = adminOsClient;
    }
    // OpenStackFilter의 attemptAuthentication의 결과물을 주입함 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String domain = (String)authentication.getDetails();

        try{
            // Unscoped Token 을 발급받아서 생성된 객체 
            OpenStackAuth openStackAuth = new OpenStackAuth(username, password, domain);
            log.info("Login succeed : [{}]", username);
            String userId = openStackAuth.getOsClient().getToken().getUser().getId();
            String defaultProjectId = adminOsClient.identity().users().get(userId).getDefaultProjectId();
            String projectId = null;
            String projectName = null;
            String domainId = null;
            String domainName = null;
            List<KeystoneProject> list = (List<KeystoneProject>) adminOsClient.identity().users().listUserProjects(userId);

            if(defaultProjectId == null){
                projectId = list.get(0).getId();
                projectName = list.get(0).getName();
                domainId = list.get(0).getDomainId();
                domainName = domain;
            }else{
                KeystoneProject keystoneProject = list.stream().filter(x->x.getId().equals(defaultProjectId)).collect(Collectors.toList()).get(0);
                projectId = defaultProjectId;
                projectName = keystoneProject.getName();
                domainId = keystoneProject.getDomainId();
                domainName = domain;
            }
            //unscoped 토큰 저장 
            UserInformation.UserInformationBuilder userInformationBuilder = UserInformation.builder().unscopedToken(openStackAuth.getOsClient().getToken());

            //Scoped Token 이 저장된 Openstack Client 객체를 가져옴 
            openStackAuth = OpenStackAuth.projectScopedAuth(username, password, domainName, projectName);

            UserInformation userInformation = userInformationBuilder.scopedToken(openStackAuth.getOsClient().getToken())
                    .projectId(projectId)
                    .projectName(projectName)
                    .domain(domainName).build();

            Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("member"));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
            usernamePasswordAuthenticationToken.setDetails(userInformation);
            return usernamePasswordAuthenticationToken;
        } catch (AuthenticationException authenticationException){
            return null;
        } catch (ClientResponseException clientResponseException){
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
