package pl.coderstrust.database.sqlDatabase;

import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.MyCompanyBuy;
import pl.coderstrust.model.counterparts.MyCompanySell;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlDatabaseTest {
  public static void main(String[] args) throws SQLException {
    System.out.println("-------- MySQL JDBC Connection Testing ------------");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Where is your MySQL JDBC Driver?");
      e.printStackTrace();
      return;
    }

    System.out.println("MySQL JDBC Driver Registered!");
    Connection connection = null;

    try {
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC", "root", "12345678");

    } catch (SQLException e) {
      System.out.println("Connection Failed! Check output console");
      e.printStackTrace();
      return;
    }

    if (connection != null) {
      System.out.println("You made it, take control your database now!");
    } else {
      System.out.println("Failed to make connection!");
    }
    Statement stmt = null;
    String query2 = "UPDATE invoicebook.invoice_entries set invoice_id=1 WHERE id=13";
    String query3 = "INSERT INTO `invoicebook`.`invoice_entries` SET  entry_id = 3,  entry_name = 'Felga',  entry_quantity = 10,  entry_vat_rate = 23, entry_net_price =1, entry_net_value=10, entry_vat_value=2.3, entry_gross_value=12.3, currency = 'PLN';";
    Buyer buyer = new MyCompanyBuy();
    Seller seller = new MyCompanySell();
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);

    Invoice givenInvoice = new Invoice(seller, new Buyer("TestBuyer", "PL123432112"));
    givenInvoice.addEntry(invoiceEntry1);
    givenInvoice.addEntry(invoiceEntry2);
    givenInvoice.addEntry(invoiceEntry3);
    givenInvoice.setInvoiceId(1);


    String query4 = "INSERT INTO invoicebook.buyers SET vat_id='" + buyer.getVatId() + "', name='"+buyer.getName() + "', address1='"+buyer.getAddress1() + "', address2='"+buyer.getAddress2() + "', zip='"+buyer.getZip() + "', account_number='"+buyer.getAccountNumber()+"'" ;
    String query5 = "INSERT INTO invoicebook.sellers SET vat_id='" + seller.getVatId() + "', name='"+seller.getName() + "', address1='"+seller.getAddress1() + "', address2='"+seller.getAddress2() + "', zip='"+seller.getZip() + "', account_number='"+seller.getAccountNumber()+"'" ;
    String query6 = "";
    System.out.println(query4);
    System.out.println(query5);
    try {
      stmt = connection.createStatement();
      Boolean ret = stmt.execute(query2);
      //     stmt.execute(query3);
      stmt.execute(query4);
      stmt.execute(query5);

      for (int i = 0; i<givenInvoice.getEntries().size(); i++){
        //      String query7 = "INSERT INTO `invoicebook`.`invoice_entries` SET  invoice_id = "+givenInvoice.getInvoiceId()+", entry_id = " +givenInvoice.getEntries().get(i).getEntryId()+",  entry_name ='" +givenInvoice.getEntries().get(i).getEntryName()+"',  entry_quantity = " +givenInvoice.getEntries().get(i).getEntryQuantity()+",  entry_vat_rate = " +givenInvoice.getEntries().get(i).getEntryVatRate()+", entry_net_price =" +givenInvoice.getEntries().get(i).getEntryNetPrice().getAmount()+", entry_net_value=" +givenInvoice.getEntries().get(i).getEntryNetValue().getAmount()+", entry_vat_value="+givenInvoice.getEntries().get(i).getEntryVatValue().getAmount()+", entry_gross_value="+givenInvoice.getEntries().get(i).getEntryGrossValue().getAmount()+", currency = '"+givenInvoice.getEntries().get(i).getEntryNetPrice().getCurrency()+"'";
        String query7 = "INSERT INTO `invoicebook`.`invoice_entries` SET  invoice_id = "+givenInvoice.getInvoiceId()+", entry_id = " +givenInvoice.getEntries().get(i).getEntryId()+",  entry_name ='" +givenInvoice.getEntries().get(i).getEntryName()+"',  entry_quantity = " +givenInvoice.getEntries().get(i).getEntryQuantity()+",  entry_vat_rate = " +givenInvoice.getEntries().get(i).getEntryVatRate()+", entry_net_price =" +givenInvoice.getEntries().get(i).getEntryNetPrice().getAmount()+", entry_net_value=" +givenInvoice.getEntries().get(i).getEntryNetValue().getAmount()+", entry_vat_value="+givenInvoice.getEntries().get(i).getEntryVatValue().getAmount()+", entry_gross_value="+givenInvoice.getEntries().get(i).getEntryGrossValue().getAmount()+", currency = '"+givenInvoice.getEntries().get(i).getEntryNetPrice().getCurrency()+"';";
        System.out.println(query7);
        stmt.execute(query7);
      }


      System.out.println("Return value is : " + ret.toString());
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }
    stmt = null;
    try {
      stmt = connection.createStatement();
      for (int i = 0; i<givenInvoice.getEntries().size(); i++){
        //      String query7 = "INSERT INTO `invoicebook`.`invoice_entries` SET  invoice_id = "+givenInvoice.getInvoiceId()+", entry_id = " +givenInvoice.getEntries().get(i).getEntryId()+",  entry_name ='" +givenInvoice.getEntries().get(i).getEntryName()+"',  entry_quantity = " +givenInvoice.getEntries().get(i).getEntryQuantity()+",  entry_vat_rate = " +givenInvoice.getEntries().get(i).getEntryVatRate()+", entry_net_price =" +givenInvoice.getEntries().get(i).getEntryNetPrice().getAmount()+", entry_net_value=" +givenInvoice.getEntries().get(i).getEntryNetValue().getAmount()+", entry_vat_value="+givenInvoice.getEntries().get(i).getEntryVatValue().getAmount()+", entry_gross_value="+givenInvoice.getEntries().get(i).getEntryGrossValue().getAmount()+", currency = '"+givenInvoice.getEntries().get(i).getEntryNetPrice().getCurrency()+"'";
        String query7 = "INSERT INTO `invoicebook`.`invoice_entries` SET  invoice_id = "+givenInvoice.getInvoiceId()+", entry_id = " +givenInvoice.getEntries().get(i).getEntryId()+",  entry_name ='" +givenInvoice.getEntries().get(i).getEntryName()+"',  entry_quantity = " +givenInvoice.getEntries().get(i).getEntryQuantity()+",  entry_vat_rate = " +givenInvoice.getEntries().get(i).getEntryVatRate()+", entry_net_price =" +givenInvoice.getEntries().get(i).getEntryNetPrice().getAmount()+", entry_net_value=" +givenInvoice.getEntries().get(i).getEntryNetValue().getAmount()+", entry_vat_value="+givenInvoice.getEntries().get(i).getEntryVatValue().getAmount()+", entry_gross_value="+givenInvoice.getEntries().get(i).getEntryGrossValue().getAmount()+", currency = '"+givenInvoice.getEntries().get(i).getEntryNetPrice().getCurrency()+"';";
        System.out.println(query7);
        stmt.execute(query7);
      }


      System.out.println("loop done ");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }
// INSERTING FULL INVOICE
    stmt = null;
    try {
      stmt = connection.createStatement();
      for (int i = 0; i<givenInvoice.getEntries().size(); i++){
        //      String query7 = "INSERT INTO `invoicebook`.`invoice_entries` SET  invoice_id = "+givenInvoice.getInvoiceId()+", entry_id = " +givenInvoice.getEntries().get(i).getEntryId()+",  entry_name ='" +givenInvoice.getEntries().get(i).getEntryName()+"',  entry_quantity = " +givenInvoice.getEntries().get(i).getEntryQuantity()+",  entry_vat_rate = " +givenInvoice.getEntries().get(i).getEntryVatRate()+", entry_net_price =" +givenInvoice.getEntries().get(i).getEntryNetPrice().getAmount()+", entry_net_value=" +givenInvoice.getEntries().get(i).getEntryNetValue().getAmount()+", entry_vat_value="+givenInvoice.getEntries().get(i).getEntryVatValue().getAmount()+", entry_gross_value="+givenInvoice.getEntries().get(i).getEntryGrossValue().getAmount()+", currency = '"+givenInvoice.getEntries().get(i).getEntryNetPrice().getCurrency()+"'";
        String query7 = "INSERT INTO `invoicebook`.`invoice_entries` SET  invoice_id = "+givenInvoice.getInvoiceId()+", entry_id = " +givenInvoice.getEntries().get(i).getEntryId()+",  entry_name ='" +givenInvoice.getEntries().get(i).getEntryName()+"',  entry_quantity = " +givenInvoice.getEntries().get(i).getEntryQuantity()+",  entry_vat_rate = " +givenInvoice.getEntries().get(i).getEntryVatRate()+", entry_net_price =" +givenInvoice.getEntries().get(i).getEntryNetPrice().getAmount()+", entry_net_value=" +givenInvoice.getEntries().get(i).getEntryNetValue().getAmount()+", entry_vat_value="+givenInvoice.getEntries().get(i).getEntryVatValue().getAmount()+", entry_gross_value="+givenInvoice.getEntries().get(i).getEntryGrossValue().getAmount()+", currency = '"+givenInvoice.getEntries().get(i).getEntryNetPrice().getCurrency()+"';";
        System.out.println(query7);
        stmt.execute(query7);
      }


      System.out.println("loop done ");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }

    stmt = null;
    String query = "select * from `invoicebook`.`invoice_entries` limit 100";
    try {
      stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String currency = rs.getString("currency");
        String id = rs.getString("id");
        String amount = rs.getString("entry_net_value");
        System.out.println("Currency " + currency + " id " + id + " amount " + amount);
      }
    } catch (SQLException e) {
      //    JDBCTutorialUtilities.printSQLException(e);
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }
  }
}
