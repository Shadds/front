package monstrecoding.controllers;

import monstrecoding.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private int counter = 4;
    private  List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message"); }});
    }};

    @GetMapping("/test1")
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("/test1/{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("test1")
    public Map<String, String> add(@RequestBody Map<String, String> message){
        message.put("id",String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    @GetMapping("/test2")
    public List<String> list2() {
        return new ArrayList<String>(){{ add("ddd"); add("ss"); }};
    }

    @GetMapping("/test3")
    public int[] test3() {
        return new int[] { 1, 2, 3, 5 };
    }

    @GetMapping("test4")
    public int[][] test4() {
        return new int[][] { {1,2}, {432,3}, {}, {0, 1, 2, 3, 4, 5, 5} };
    }
}
