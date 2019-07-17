package mate.academy.service.scheduler;

import mate.academy.dao.CategoryRepository;
import mate.academy.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultScheduler {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    private List<String> urls = Arrays.asList("http://google.com.ua", "http://wix.com");

    //never do this - not thread safe
    private int counter = 1;

//    @Scheduled(initialDelay = 5000, fixedDelay = 7000)
//    @Scheduled(cron = "0 0 12 1/1 * ? *")
//    public void getStatusesFromUrls() {
//        System.out.println("##################");
//        urls.stream()
//                .map(u -> restTemplate.exchange(u, HttpMethod.GET, null, String.class))
//                .peek(System.out::println)
//                .collect(Collectors.toList());
//    }

//    @Scheduled(initialDelay = 5000, fixedDelay = 7000)
    public void pollAndPrintAllCategories() {
        System.out.println("##################");
        categoryRepository.findAll().stream()
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

//    @Scheduled(initialDelay = 7000, fixedDelay = 7000)
    public void addNewCategory() {
        categoryRepository.save(new Category(null, "test_" + counter, "test_" + counter));
        counter++;
    }
}
