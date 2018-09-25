package t04; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/driver" , method = {RequestMethod.POST, RequestMethod.GET})
    public Driver driver0 (@RequestParam(value="name", defaultValue="John") String driverName , @RequestParam(value="seating", defaultValue="-1000") String seatingInput, @RequestParam (value="stops", defaultValue="-1000") String stopsInput, @RequestParam(value="price", defaultValue="-1000") String priceInput) {
    	Driver driver = new Driver (counter.incrementAndGet(), driverName); 
    	driver.setSeating(Long.parseLong(seatingInput)); 
    	driver.setStops(Long.parseLong(stopsInput)); 
    	driver.setPrice(Long.parseLong(priceInput)); 
        return driver; 
    }
}
