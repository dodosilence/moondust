package cc.moondust.mim.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by Tristan on 16/6/29.
 */
@Controller
@RequestMapping("/")
public class IndexPageAction {
    @RequestMapping({"index.html","index","/","index.html?*"})
    public  String homePage(){

        return "index";
    }


    @RequestMapping("json")
    @ResponseBody
    public Object jsonTest(){
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("name","json");
        res.put("password","love201314");
        res.put("nick","小三");
        return res;
    }







}
