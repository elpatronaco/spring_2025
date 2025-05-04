package edu.uoc.epcsd.user.application.rest;

import edu.uoc.epcsd.user.application.rest.request.CreateDigitalItemRequest;
import edu.uoc.epcsd.user.domain.DigitalItem;
import edu.uoc.epcsd.user.domain.DigitalItemStatus;
import edu.uoc.epcsd.user.domain.service.DigitalItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/digitalItem")
public class DigitalItemRESTController {

    private final DigitalItemService digitalItemService;

    @GetMapping("/allItems")
    @ResponseStatus(HttpStatus.OK)
    public List<DigitalItem> getAllDigitalItem() {
        log.trace("getAllDigitalItem");

        return digitalItemService.findAllDigitalItem();
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/{digitalItemId}"
    // and create the method getDigitalItemById(@PathVariable @NotNull Long digitalItemId)
    // which call the corresponding getDigitalItemById method

    @GetMapping("/{digitalItemId}")
    public ResponseEntity<DigitalItem> getDigitalItemById(@PathVariable @NotNull Long digitalItemId) {
        log.trace("getDigitalItemById");

        return digitalItemService.getDigitalItemById(digitalItemId).map(item -> ResponseEntity.ok().body(item))
                .orElse(ResponseEntity.notFound().build());
    }


    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/digitalItemBySession"
    // and create the method findDigitalItemBySession(@RequestParam @NotNull Long digitalSessionId)
    // which call the corresponding findDigitalItemBySession method

    @GetMapping("/digitalItemBySession")
    public List<DigitalItem> getDigitalItemBySession(@RequestParam @NotNull Long digitalSessionId) {
        log.trace("getDigitalItemBySession");

        return digitalItemService.findDigitalItemBySession(digitalSessionId);
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/addItem"
    // and create the method addDigitalItem(@RequestBody @Valid CreateDigitalItemRequest createDigitalItemRequest)
    // which call the corresponding addDigitalItemm method

    @PostMapping("/addItem")
    public ResponseEntity<Long> addDigitalItem(@RequestBody @Valid CreateDigitalItemRequest createDigitalItemRequest) {
        log.trace("addDigitalItem");

        log.trace("Creating product {}", createDigitalItemRequest);

        try {
            DigitalItem item = DigitalItem.builder()
                    .digitalSessionId(createDigitalItemRequest.getDigitalSessionId())
                    .description(createDigitalItemRequest.getDescription())
                    .lon(createDigitalItemRequest.getLon())
                    .lat(createDigitalItemRequest.getLat())
                    .link(createDigitalItemRequest.getLink())
                    .status(DigitalItemStatus.AVAILABLE)
                    .build();

            Long itemId = digitalItemService.addDigitalItem(item);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{digitalItemId}")
                    .buildAndExpand(itemId)
                    .toUri();

            return ResponseEntity.created(uri).body(itemId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The specified digital session " + createDigitalItemRequest.getDigitalSessionId() + " does not exist.", e);
        }
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/updateItem/{digitalItemId}"
    // and create the method updateDigitalItem(@PathVariable @NotNull Long digitalItemId, @RequestBody @Valid CreateDigitalItemRequest updateDigitalItemRequest)
    // which call the corresponding updateDigitalItem method

    @PutMapping("/updateItem/{digitalItemId}")
    public ResponseEntity<Boolean> updateDigitalItem(@PathVariable @NotNull Long digitalItemId, @RequestBody @Valid CreateDigitalItemRequest updateDigitalItemRequest) {
        log.trace("updateDigitalItem");

        log.trace("Updating product {} with {}", digitalItemId, updateDigitalItemRequest);

        try {
            digitalItemService.updateDigitalItem(digitalItemId, updateDigitalItemRequest.getDescription(), updateDigitalItemRequest.getLink(), updateDigitalItemRequest.getLat(), updateDigitalItemRequest.getLon());

            return ResponseEntity.ok().body(true);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The specified digital session " + updateDigitalItemRequest.getDigitalSessionId() + " does not exist.", e);
        }
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/reviewDigitalItem/{digitalItemId}"
    // and create the method setDigitalItemForReview(@PathVariable @NotNull Long digitalItemId)
    // which call the corresponding setDigitalItemForReview method

    @PutMapping("/reviewDigitalItem/{digitalItemId}")
    public ResponseEntity setDigitalItemForReview(@PathVariable @NotNull Long digitalItemId) {
        log.trace("setDigitalItemForReview");

        log.trace("Setting digital item {} for review", digitalItemId);

        try {
            digitalItemService.setDigitalItemForReview(digitalItemId);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The specified digital item " + digitalItemId + " does not exist.", e);
        }
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/approveDigitalItem/{digitalItemId}"
    // and create the method approvePendingDigitalItem(@PathVariable @NotNull Long digitalItemId)
    // which call the corresponding approvePendingDigitalItem method

    @PutMapping("/approveDigitalItem/{digitalItemId}")
    public ResponseEntity<Boolean> approvePendingDigitalItem(@PathVariable @NotNull Long digitalItemId) {
        log.trace("approvePendingDigitalItem");

        log.trace("Setting status of digital item {} to AVAILABLE", digitalItemId);

        try {
            digitalItemService.approvePendingDigitalItem(digitalItemId);

            return ResponseEntity.ok().body(true);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/rejectDigitalItem/{digitalItemId}"
    // and create the method rejectPendingDigitalItem(@PathVariable @NotNull Long digitalItemId)
    // which call the corresponding rejectPendingDigitalItem method

    @PutMapping("/rejectDigitalItem/{digitalItemId}")
    public ResponseEntity<Boolean> rejectPendingDigitalItem(@PathVariable @NotNull Long digitalItemId) {
        log.trace("rejectPendingDigitalItem");

        log.trace("Setting status of digital item {} to REJECTED", digitalItemId);

        try {
            digitalItemService.rejectPendingDigitalItem(digitalItemId);

            return ResponseEntity.ok().body(true);
        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // TODO: add the code for the missing system operations here: 
    // use the corresponding mapping HTTP request annotation with the parameter: "/dropItem/{digitalItemId}"
    // and create the method dropDigitalItem(@PathVariable @NotNull Long digitalItemId)
    // which call the corresponding dropDigitalItem method

    @DeleteMapping("/dropItem/{digitalItemId}")
    public ResponseEntity<Boolean> dropItem(@PathVariable @NotNull Long digitalItemId) {
        log.trace("dropItem");

        log.trace("Dropping digital item with id {}", digitalItemId);

        try {
            digitalItemService.dropDigitalItem(digitalItemId);

            return ResponseEntity.ok().body(true);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

}
