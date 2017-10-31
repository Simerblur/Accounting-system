package pl.coderstrust.database.sql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.MyCompanyBuy;
import pl.coderstrust.model.counterparts.MyCompanySell;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
@Service
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "sqlDatabase")
public class SqlDatabase implements Database {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void saveInvoice(Invoice invoice) {
    Query querySellers = entityManager
        .createNativeQuery("SELECT * FROM test.seller;", Seller.class);
    List<Seller> sellers = querySellers.getResultList();

    Query queryBuyers = entityManager.createNativeQuery("SELECT * FROM test.buyer;", Buyer.class);
    List<Buyer> buyers = queryBuyers.getResultList();
    int sellerCheck = 0;
    int buyerCheck = 0;
    for (Seller seller : sellers) {
      System.out.println(seller.getVatId().toString());
      if (seller.getVatId().equals(invoice.getSeller().getVatId())) {
        sellerCheck = 1;
        System.out.println(sellerCheck);
      }
    }
    if (sellerCheck == 0) {
      entityManager.persist(invoice.getSeller());
    }
    for (Buyer buyer : buyers) {
      System.out.println(buyer.getVatId().toString());
      if (buyer.getVatId().equals(invoice.getBuyer().getVatId())) {
        buyerCheck = 1;
        System.out.println(buyerCheck);
      }
    }
    if (buyerCheck == 0) {
      entityManager.persist(invoice.getBuyer());
    }
    entityManager.persist(invoice.getNetTotalAmount());
    entityManager.persist(invoice.getGrossTotalAmount());
    for (InvoiceEntry entry : invoice.getEntries()) {
      entityManager.persist(entry.getEntryNetPrice());
      entityManager.persist(entry.getEntryNetValue());
      entityManager.persist(entry.getEntryVatValue());
      entityManager.persist(entry.getEntryGrossValue());
      entityManager.persist(entry);
    }
    entityManager.persist(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    Query query = entityManager.createNativeQuery("SELECT * FROM test.invoice;", Invoice.class);
    return query.getResultList();
  }

  @Override
  public boolean removeInvoice(int invoiceId) {
    return false;
  }

  @Override
  public boolean replaceInvoice(int invoiceId, Invoice invoice) {
    return false;
  }
}
