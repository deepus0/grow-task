package au.com.deep.grow.mapper;

import au.com.deep.grow.model.ui.Contact;
import com.xero.models.accounting.Invoices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

@Component
@Slf4j
public class ContactMapper {

    public Contact map(com.xero.models.accounting.Contact response) {
        Contact contact = new Contact();
        contact.setContactId(response.getContactID().toString());
        contact.setCustomer(response.getIsCustomer());
        contact.setSupplier(response.getIsSupplier());
        contact.setName(response.getName());
        return contact;
    }

    public Contact map(Invoices invoices, Contact contact) {
        contact.setTotalAmount(0);
        LocalDate lastYear = LocalDate.now().minus(1, ChronoUnit.YEARS);
        invoices.getInvoices().stream()
                .filter(invoice -> invoice.getDateAsDate().isAfter(lastYear))
                .filter(invoice -> invoice.getContact().getContactID().toString().equals(contact.getContactId()))
                // TODO confirm if this totalAmount can be used...
                .forEach(invoice -> contact.setTotalAmount(contact.getTotalAmount() + invoice.getTotal()));
        return contact;
    }
}
