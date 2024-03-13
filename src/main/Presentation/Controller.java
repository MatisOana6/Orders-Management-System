package src.main.Presentation;

import src.main.Model.Bill;
import src.main.bll.ClientBLL;
import src.main.Model.Client;
import src.main.Model.Order;
import src.main.Model.Product;
import src.main.bll.LogBLL;
import src.main.bll.OrderBLL;
import src.main.bll.ProductBLL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Aceasta clasa implementeaza functionalitatile butoanelor din GUI.
 */

public class Controller implements ActionListener {
    private View v;

    public Controller(View v) {
        this.v = v;
    }


    /**
     metodă specifică interfeței ActionListener, ce trebuie implementată în mod obligatoriu.
     În această metodă se verifică cu câte un if, care buton s-a apăsat, și în funcție de butonul apăsat, se execută acțiunile necesare
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == v.getClientOp()) {
            v.clienOpFrame();
        }
        if(source == v.getProdusOp()) {
            v.ProdusOpFrame();
        }
        if(source == v.getOrderOp()) {
            v.OrderOpFrame();
        }
        if(source == v.getBillOp()) {
            v.BillOpFrame();
        }


        if(source == v.getHome()) {
            v.getFrame2().dispose();
            View newView = new View();
        }

        if(source == v.getFindAllClients()) {
            ClientBLL bll = new ClientBLL();
            List<Client> result = bll.findAll();
            Reflection.getRows(result);
        }

        if(source == v.getFindAllBills()) {
            LogBLL bll = new LogBLL();
            List<Bill> result = bll.findAll();
            Reflection.getRows(result);
        }

        if(source == v.getFindClientById()) {
            ClientBLL bll = new ClientBLL();
            int id = Integer.parseInt(v.getValue1());
            Client c = bll.findClientById(id);
            List<Client> result = new ArrayList<>();
            result.add(c);
            Reflection.getRows(result);
        }

        if(source == v.getFindClientByName()) {
            ClientBLL bll = new ClientBLL();
            String name = v.getValue1();
            List<Client> result = bll.findByName(name);
            Reflection.getRows(result);
        }

        if(source == v.getFindClientByEmail()) {
            ClientBLL bll = new ClientBLL();
            String name = v.getValue1();
            List<Client> result = bll.findByEmail(name);
            Reflection.getRows(result);
        }

        if(source == v.getDeleteClientByID()) {
            ClientBLL bll = new ClientBLL();
            int id = Integer.parseInt(v.getClientDeleteElement());
            bll.deleteByID(id);
        }

        if(source == v.getDeleteClientByName()) {
            ClientBLL bll = new ClientBLL();
            String name = v.getClientDeleteElement();
            bll.deleteByName(name);
        }

        if(source == v.getDeleteClientByEmail()) {
            ClientBLL bll = new ClientBLL();
            String email = v.getClientDeleteElement();
            bll.deleteByMail(email);
        }

        if(source == v.getFindAllProducts()) {
            ProductBLL bll = new ProductBLL();
            List<Product> result = bll.findAll();
            Reflection.getRows(result);
        }

        if(source == v.getFindProdusById()) {
            ProductBLL bll = new ProductBLL();
            int id = Integer.parseInt(v.getProdusSearchElement());
            Product p = bll.findProdusById(id);
            List<Product> result = new ArrayList<>();
            result.add(p);
            Reflection.getRows(result);
        }

        if(source == v.getFindProdusByName()) {
            ProductBLL bll = new ProductBLL();
            String name = v.getProdusSearchElement();
            Product p = bll.findByName(name);
            List<Product> result = new ArrayList<>();
            result.add(p);
            Reflection.getRows(result);
        }

        if(source == v.getFindProdusByPrice()) {
            ProductBLL bll = new ProductBLL();
            Double price = Double.parseDouble(v.getProdusSearchElement());
            List <Product> p = bll.findByPrice(price);
            Reflection.getRows(p);
        }

        if(source == v.getFindProdusByQuantity()) {
            ProductBLL bll = new ProductBLL();
            Integer quantity = Integer.parseInt(v.getProdusSearchElement());
            List <Product> p = bll.findByQuantity(quantity);
            Reflection.getRows(p);
        }

        if(source == v.getDeleteProdusByName()) {
            ProductBLL bll = new ProductBLL();
            String name = v.getProdusDeleteValue();
            bll.deleteByName(name);
        }

        if(source == v.getDeleteProdusByID()) {
            ProductBLL bll = new ProductBLL();
            int id = Integer.parseInt(v.getProdusDeleteValue());
            bll.deleteByID(id);
        }

        if(source == v.getDeleteProdusByPrice()) {
            ProductBLL bll = new ProductBLL();
            double price = Double.parseDouble(v.getProdusDeleteValue());
            bll.deleteByPrice(price);
        }

        if(source == v.getDeleteProdusByQuantity()) {
            ProductBLL bll = new ProductBLL();
            int quantity = Integer.parseInt(v.getProdusDeleteValue());
            bll.deleteByQuantity(quantity);
        }

        if(source == v.getUpdateProdus()) {
            v.updateProductFrame();
        }

        if(source == v.getFindAllOrders()) {
            OrderBLL bll = new OrderBLL();
            List<Order> result = bll.findAll();
            Reflection.getRows(result);
        }

        if(source == v.getFindOrderById()) {
            OrderBLL bll = new OrderBLL();
            int id = Integer.parseInt(v.getOrderSearchValue());
            Order o = bll.findOrderById(id);
            List <Order> result = new ArrayList<>();
            result.add(o);
            Reflection.getRows(result);
        }

        if(source == v.getFindOrderByClientId()) {
            OrderBLL bll = new OrderBLL();
            int id = Integer.parseInt(v.getOrderSearchValue());
            List <Order> result = new ArrayList<>();
            result = bll.findOrderByClientId(id);
            Reflection.getRows(result);
        }

        if(source == v.getFindOrderByProdusId()) {
            OrderBLL bll = new OrderBLL();
            int id = Integer.parseInt(v.getOrderSearchValue());
            List <Order> result = new ArrayList<>();
            result = bll.findOrderByProductId(id);
            Reflection.getRows(result);
        }


        if(source == v.getDeleteOrderByID())  {
            OrderBLL bll = new OrderBLL();
            int id = Integer.parseInt(v.getOrdersDeleteElement());
            System.out.println(id);
            bll.delete(id);
        }

        if(source == v.getDeleteOrderByClientID())  {
            OrderBLL bll = new OrderBLL();
            int id = Integer.parseInt(v.getOrdersDeleteElement());
            bll.deleteByClientID(id);
        }

        if(source==v.getInsertClient()) {
            v.insertClientFrame();
        }

        if(source == v.getInsertClient2()) {
            ClientBLL bll = new ClientBLL();
            String name = v.getName();
            String email = v.getEmail();
            bll.insert(name,email);

        }

        if(source == v.getInsertPodus()) {
            v.insertProductFrame();
        }

        if(source == v.getInsertPodus2()) {
            ProductBLL bll = new ProductBLL();
            String name = v.getProdusValue2();
            int quantity = Integer.parseInt(v.getProdusValue3());
            double price = Double.parseDouble(v.getProdusValue4());
            bll.insert(name,quantity, price);
        }

        if(source == v.getUpdateClient()) {
            v.updateClientFrame();
        }

        if(source == v.getUpdateByID()) {
            v.updateClientByIDFrame();
            ClientBLL bll = new ClientBLL();
            int id = Integer.parseInt(v.getData());
            Client c = bll.findClientById(id);
            v.setID(c.getId());
            v.setClientName(c.getName());
            v.setEmail(c.getEmail());
        }

        if(source == v.getUpdateByID2()) {
            ClientBLL bll = new ClientBLL();
            int id = Integer.parseInt(v.getData());
            String name = v.getNewName();
            String email = v.getNewEmail();
            System.out.println(email);
            bll.updateByID(id,name,email);
        }

        if(source == v.getUpdateByEmail()) {
            v.updateClientByEmailFrame();
            ClientBLL bll = new ClientBLL();
            String email = v.getData();
            Client c = bll.findByEmail(email).get(0);
            v.setID(c.getId());
            v.setClientName(c.getName());
            v.setEmail(c.getEmail());
        }


        if(source == v.getUpdateByEmail2()) {
            ClientBLL bll = new ClientBLL();
            String data = v.getData();
            String name = v.getNewName();
            String email = v.getNewEmail();
            bll.updateByEmail(data,name,email);
        }

        if(source == v.getUpdateByName()) {
            v.updateClientByNameFrame();
            ClientBLL bll = new ClientBLL();
            String name = v.getData();
            Client c = bll.findByName(name).get(0);
            v.setID(c.getId());
            v.setClientName(c.getName());
            v.setEmail(c.getEmail());
        }

        if(source == v.getUpdateByName2()) {
            ClientBLL bll = new ClientBLL();
            String data = v.getData();
            String name = v.getNewName();
            String email = v.getNewEmail();
            bll.updateByName(data,name,email);
        }

        if(source == v.getUpdateProductByID()) {
            v.UpdateProductByIDFrame();
            ProductBLL bll = new ProductBLL();
            Integer id = Integer.parseInt(v.getData());
            Product p = bll.findProdusById(id);
            v.setProdcutID(String.valueOf(p.getId()));
            v.setProductName(p.getName());
            v.setProductQuantity(String.valueOf(p.getQuantity()));
            v.setProductPrice(String.valueOf(p.getPrice()));
        }

        if(source == v.getUpdateProductByName()) {
            v.UpdateProductByNameFrame();
            ProductBLL bll = new ProductBLL();
            String name = v.getData();
            Product p = bll.findByName(name);
            v.setProdcutID(String.valueOf(p.getId()));
            v.setProductName(p.getName());
            v.setProductQuantity(String.valueOf(p.getQuantity()));
            v.setProductPrice(String.valueOf(p.getPrice()));
        }

        if(source == v.getUpdateProductByID2()) {
            ProductBLL bll = new ProductBLL();
            int data = Integer.parseInt(v.getData());
            String name = v.getNewProductName();
            int quantity = Integer.parseInt(v.getNewQuantity());
            double price = Double.parseDouble(v.getNewProductPrice());
            bll.updateById(data,name,quantity, price);
        }

        if(source == v.getUpdateProductByName2()) {
            ProductBLL bll = new ProductBLL();
            String data = v.getData();
            String name = v.getNewProductName();
            int quantity = Integer.parseInt(v.getNewQuantity());
            double price = Double.parseDouble(v.getNewProductPrice());
            bll.updateByName(data, name, quantity, price);
        }

        if(source == v.getUpdateProductByQuantity()) {
            v.UpdateProductByQuantityFrame();
            ProductBLL bll = new ProductBLL();
            Integer quantity = Integer.parseInt(v.getData());
            Product p = bll.findByQuantity(quantity).get(0);
            v.setProdcutID(String.valueOf(p.getId()));
            v.setProductName(p.getName());
            v.setProductQuantity(String.valueOf(p.getQuantity()));
            v.setProductPrice(String.valueOf(p.getPrice()));
        }

        if(source == v.getUpdateProductByQuantity2()) {
            ProductBLL bll = new ProductBLL();
            int data = Integer.parseInt(v.getData());
            String name = v.getNewProductName();
            int quantity = Integer.parseInt(v.getNewQuantity());
            double price = Double.parseDouble(v.getNewProductPrice());
            bll.updateByQuantity(data, name, quantity, price);
        }

        if(source == v.getUpdateProductByPrice()) {
            v.UpdateProductByPriceFrame();
            ProductBLL bll = new ProductBLL();
            Double price = Double.parseDouble(v.getData());
            Product p = bll.findByPrice(price).get(0);
            v.setProdcutID(String.valueOf(p.getId()));
            v.setProductName(p.getName());
            v.setProductQuantity(String.valueOf(p.getQuantity()));
            v.setProductPrice(String.valueOf(p.getPrice()));
        }

        if(source == v.getUpdateProductByPrice2()) {
            ProductBLL bll = new ProductBLL();
            double data = Double.parseDouble(v.getData());
            String name = v.getNewProductName();
            int quantity = Integer.parseInt(v.getNewQuantity());
            double price = Double.parseDouble(v.getNewProductPrice());
            bll.updateByPrice(data, name, quantity, price);
        }

        if(source == v.getUpdateOrder()) {
            v.UpdateOrder();
        }

        if(source == v.getUpdateOrderByID()) {
            v.UpdateOrderByID();
            OrderBLL bll = new OrderBLL();
            int id = Integer.parseInt(v.getOrderData());
            Order o = bll.findOrderByClientId(id).get(0);
            v.setOrdersProductID(String.valueOf(o.getIdProduct()));
            v.setClientID(String.valueOf(o.getIdClient()));
            v.setPriceOfOrder(String.valueOf(o.getPrice()));
            v.setOrderQuantity(String.valueOf(o.getQuantity()));
        }

        if(source == v.getUpdateOrderByID2()) {
            OrderBLL bll = new OrderBLL();
            Integer id = Integer.parseInt(v.getOrderData());
            Integer idClient = Integer.parseInt(v.getClientID());
            Integer idProduct = Integer.parseInt(v.getOrdersProductID());
            Integer quantity = Integer.parseInt(v.getOrderQuantity());
            Double price = Double.parseDouble(v.getOrderPrice());
            bll.updateByID(id, idClient, idProduct, price, quantity);
        }

        if(source == v.getInsertOrder()) {
            v.insertOrder();
        }

        if(source == v.getInsertOrder2()) {
            OrderBLL bll = new OrderBLL();
            Integer idClient = Integer.parseInt(v.getClientID());
            Integer idProduct = Integer.parseInt(v.getOrdersProductID());
            Integer quantity = Integer.parseInt(v.getOrderQuantity());
            boolean x = bll.insertOrder(idClient, idProduct, quantity);
            if(!x) {
                v.displayErrorMessage();
            }

        }
            if (source == v.getInsertBill()) {
                v.insertBill(); // Call the insertBill method to show the GUI window
        }

        if(source == v.getInsertBill2()) {
            LogBLL bll = new LogBLL();
            Integer idClient2 = Integer.parseInt(v.getClientID2());
            Integer idProduct2 = Integer.parseInt(v.getOrdersProductID2());
            Integer quantity2 =  Integer.parseInt(v.getOrderQuantity2());
            boolean x = bll.insertBill(idClient2, idProduct2, quantity2);
            if(!x) {
                v.displayErrorMessage();
            }

        }

        if(source == v.getFindBillByClientID()) {
            LogBLL bll = new LogBLL();
            int id = Integer.parseInt(v.getBillSearchElement());
            List <Bill> result = new ArrayList<>();
            result = bll.findBillByClientId(id);
            Reflection.getRows(result);
        }

        if(source == v.getFindBillByProductId()) {
            LogBLL bll = new LogBLL();
            int id = Integer.parseInt(v.getBillSearchElement());
            List <Bill> result = new ArrayList<>();
            result = bll.findBillByProductId(id);
            Reflection.getRows(result);
        }
    }
}