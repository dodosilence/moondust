package cc.moondust.mim.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Tristan on 16/6/30.
 */
@RequestMapping("socket")
@Controller
public class SocketConnectorAction {


    @RequestMapping("/connector")
    public String socketPage() {

        return "socket/connector";
    }


}
