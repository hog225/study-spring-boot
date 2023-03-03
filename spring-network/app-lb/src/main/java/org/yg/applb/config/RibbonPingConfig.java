package org.yg.applb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(
        name = "ping-a-server",
        configuration = RibbonConfig.class)
@RequiredArgsConstructor
public class RibbonPingConfig {
    private final RestTemplate restTemplate;

    @RequestMapping("/server-location")
    public String serverLocation() {
        return this.restTemplate.getForObject(
                "http://ping-server/locaus", String.class);
    }

}
