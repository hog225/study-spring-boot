package org.yg.practice.security.configures;

import org.apache.tomcat.util.bcel.Const;
import org.yg.practice.security.data.Constants;
import org.yg.practice.security.data.OpenStackAuth;
import org.openstack4j.api.OSClient;
import org.openstack4j.openstack.OSFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class BeanInitializer {
    // 사용자 요청이 있을때 마다 사용되는 OSClient
    @Bean
    @RequestScope
    public OSClient.OSClientV3 osClient(){
        //Session 에 있는 token id로 객체를 생성
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String scopedTokenId = (String) httpServletRequest.getSession().getAttribute("scopedTokenId");

        OpenStackAuth openStackAuth = OpenStackAuth.projectScopedAuth(Constants.ADMIN_NAME, Constants.ADMIN_PASSWORD, "Default", Constants.ADMIN_PROJECT);
        if(openStackAuth.validateToken(scopedTokenId)){
            openStackAuth.setToken(openStackAuth.getTokenDetails(scopedTokenId));
        }

        return openStackAuth.getOsClient();
    }

    // Service User로 된 Admin Client가 만들어 진다. 
    @Bean
    public OSClient.OSClientV3 adminOsClient(){
        return OpenStackAuth.projectScopedAuth(Constants.ADMIN_NAME, Constants.ADMIN_PASSWORD, "Default", Constants.ADMIN_PROJECT).getOsClient();
    }

}
