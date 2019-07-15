package ml.fangchieh.community.controller;

import ml.fangchieh.community.controller.Provider.Githubprovider;
import ml.fangchieh.community.controller.dto.AccessTokenDTO;
import ml.fangchieh.community.controller.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController{
    @Autowired
    private Githubprovider githubprovider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String index(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubprovider.getAccessToken(accessTokenDTO);
        GithubUser user = githubprovider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
