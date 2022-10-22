import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";
        StringBuilder result = new StringBuilder();

        ResponseEntity<String> get = restTemplate.getForEntity(url, String.class);
        System.out.println(get.getBody());
        String cookie = get.getHeaders().get("Set-Cookie").get(0);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 3);
        map1.put("name", "James");
        map1.put("lastName", "Brown");
        map1.put("age", 35);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookie);
        HttpEntity<Map<String, Object>> httpEntity1 = new HttpEntity<>(map1, headers);
        ResponseEntity<String> post = restTemplate.postForEntity(url,httpEntity1, String.class);
        result.append(post.getBody());

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 3);

        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("id", 3);
        updateUser.put("name", "Thomas");
        updateUser.put("lastName", "Shelby");
        updateUser.put("age", 35);
        HttpEntity<Map<String, Object>> httpEntity2 = new HttpEntity<>(updateUser, headers);
        ResponseEntity<String> patch = restTemplate.exchange(url, HttpMethod.PUT, httpEntity2, String.class, map2);
        result.append(patch.getBody());

        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 3);
        HttpEntity<Map<String, Object>> httpEntity3 = new HttpEntity<>(map3, headers);
        ResponseEntity<String> delete = restTemplate.exchange(url + "/3", HttpMethod.DELETE, httpEntity3, String.class);
        result.append(delete.getBody());

        System.out.println();
        System.out.println(result);
    }
}
