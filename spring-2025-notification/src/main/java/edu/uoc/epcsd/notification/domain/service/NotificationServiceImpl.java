package edu.uoc.epcsd.notification.domain.service;

import edu.uoc.epcsd.notification.application.kafka.ProductMessage;
import edu.uoc.epcsd.notification.application.rest.dtos.GetProductResponse;
import edu.uoc.epcsd.notification.application.rest.dtos.GetUserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${userService.getUsersToAlert.url}")
    private String userServiceUrl;

    @Value("${productService.getProductDetails.url}")
    private String productServiceUrl;

    @Override
    public void notifyProductAvailable(ProductMessage productMessage) {

        // TODO: Use RestTemplate with the above userServiceUrl to query the User microservice in order to get the users that have an alert for the specified product (the date specified in the parameter may be the actual date: LocalDate.now()).
        //  Then simulate the email notification for the alerted users by logging a line with INFO level for each user saying "Sending an email to user " + the user fullName

        LocalDate now = LocalDate.now();
        String isoDate = now.format(DateTimeFormatter.ISO_DATE);

        // Check if the product is available
        try {
            new RestTemplate().getForEntity(productServiceUrl, GetProductResponse.class, productMessage.getProductId());
        } catch (RestClientException e) {
            log.error("Error getting product details", e);

            return;
        }

        try {
            GetUserResponse[] users = new RestTemplate()
                    .getForEntity(userServiceUrl, GetUserResponse[].class, isoDate)
                    .getBody();

            if (users == null) {
                log.error("Error getting users to alert");

                return;
            }

            for (GetUserResponse user : users) {
                // Simulate sending an email
                log.info("Sending an email to user {}", user.getFullName());
            }
        } catch (RestClientException e) {
            log.error("Error getting users to alert", e);
        }

    }
}
