@Controller
public class ClientForwardController {
    @GetMapping("/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }  
}
