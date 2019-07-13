package ml.fangchieh.community.controller;

import ml.fangchieh.community.controller.Provider.Githubprovider;
import ml.fangchieh.community.controller.dto.AccessTokenDTO;
import ml.fangchieh.community.controller.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class AuthorizeController{
    @Autowired
    private Githubprovider githubprovider;
    @GetMapping("/callback")
    public String index(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("f402f9db124aec7e1489");
        accessTokenDTO.setClient_secret("ca5b28900d11a9967bb875f2840c4d1541ae363b");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        String accessToken = githubprovider.getAccessToken(accessTokenDTO);
        GithubUser user = githubprovider.getUser(accessToken);
        return "index";
    }
}
