package me.keerthimeda.prototypes.hapidomaindemo;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.AuditEvent;
import ca.uhn.fhir.rest.api.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Date;

@Component
@RestController
@RequestMapping("/infra/Audit")
public class AuditController {

    private final FhirContext context;

    public AuditController() {
        context = FhirContext.forDstu2();
    }

    @GetMapping(value = "{id}", produces = Constants.CT_FHIR_JSON_NEW)
    public ResponseEntity<Mono<String>> getById(@PathVariable String id) {
        // Test Code.

        if (id.equals("GIVEMEANEVENT")) {
            AuditEvent event = new AuditEvent();
            event.setId(id);
            event.getSource().getIdentifier().setSystem("urn:server").setValue("NEPTUNE");
            event.getEvent().getType().setSystem("http://hl7.org/fhir/audit-event-type").setCode("110100");
            event.getEvent().setDateTimeWithMillisPrecision(new Date());

            String resourceString = context.newJsonParser().encodeResourceToString(event);

            return ResponseEntity.ok(Mono.just(resourceString));
        }

        return ResponseEntity.notFound().build();
    }
}
