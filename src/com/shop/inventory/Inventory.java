package com.shop.inventory;

import com.shop.customer.Customer;
import com.shop.product.Product;
import com.shop.product.ProductType;
import com.shop.util.Util;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

public class Inventory {

    private HashMap<Product, Integer> products;
    private List<Customer> listOfCustomers;
    private Scanner scanner;

    public Inventory(List<Customer> listOfCustomers) {
        this.products = new HashMap<>();
        this.listOfCustomers = listOfCustomers;
        this.scanner = Util.SCANNER;
        dummyProducts();
    }

    private void dummyProducts() {
        for (int i = 0; i < 4; i++) {
            Product product = new Product(("TestProduct"),
                    new BigDecimal(123), ProductType.OTHER, "Description.");
            Product otherProduct = new Product("OtherProduct",
                    new BigDecimal(456), ProductType.OTHER, "Description.");
            addProductToInventory(product);
            addProductToInventory(otherProduct);
        }
    }

    public void addProductToInventory(Product addProduct) {
        Product findProduct = findProduct(addProduct);
        if (!getProducts().containsKey(findProduct)) {
            getProducts().put(findProduct, 1);
        } else {
            getProducts().put(findProduct, getProducts().get(findProduct) + 1);
        }
    }

    public void removeProductFromInventory(Product product) {
        Product findProduct = findProduct(product);
        if (getProducts().get(findProduct) != 0) {
            getProducts().put(findProduct, getProducts().get(findProduct)-1);
        } else {
            getProducts().put(findProduct, 0);
        }
    }

    public void removeProduct(String productName, int removeProductAmount, int amountOfTheProductAvailableAmount) {
        Product findProduct = findProduct(productName);
        if (findProduct != null && removeProductAmount > 0) {
            IntStream.range(0, removeProductAmount)
                    .forEach(i -> getProducts().put(findProduct, getProducts().get(findProduct)-1));
            setProducts(getProducts());
            System.out.println("The " + productName
                    + "is successfully removed!" +
                    "\nIt had been modified from: " + amountOfTheProductAvailableAmount + " to: " + getProducts().get(findProduct) + ".");
        } else {
            System.out.println("The " + productName + " was not successfully removed!");
        }
    }

    public int getAmountOfProduct(String productName) {
        Product findProduct = findProduct(productName);
        if (products.containsKey(findProduct)) {
            return products.get(findProduct);
        }
        return 0;
    }

    public void deleteProductFromInventory(Customer customer, String productName) {
        Product findProduct = findProduct(productName);
        Optional<Product> cartProduct = getProductFromCustomerCart(customer, findProduct);
        List<Product> customerCartProductList = customerCartProductList(customer);
        if (cartProduct.isPresent()) {
            customerCartProductList.remove(cartProduct.get());
            customer.getCart().setListOfProducts(customerCartProductList);
        }
        getProducts().remove(findProduct);
        setProducts(getProducts());
        System.out.println("The " + productName + " is successfully deleted!");
    }

    public void updateProduct(Customer customer, String selectedProduct) {
        Product getUpdatedProduct = updateProductInitialization(selectedProduct);
            Optional<Product> cartProduct = getProductFromCustomerCart(customer, getUpdatedProduct);
            List<Product> customerCartProductList = customerCartProductList(customer);
        if (cartProduct.isPresent()) {
            int getIndexOfTheProductFromCart = customerCartProductList.indexOf(cartProduct.get());
            customerCartProductList.set(getIndexOfTheProductFromCart, cartProduct.get());
            customer.getCart().setListOfProducts(customerCartProductList);
        }
    }

    private Product updateProductInitialization(String selectedProduct) {
        Product findProduct = findProduct(selectedProduct);
        Product updateProduct = createUpdateProductWithInformation(selectedProduct);
        if (findProduct != null) {
            if (updateProduct.getDescription() != null) {
                findProduct.setDescription(updateProduct.getDescription());
            }
            if (updateProduct.getName() != null) {
                findProduct.setName(updateProduct.getName());
            }
            if (updateProduct.getType() != null) {
                findProduct.setType(updateProduct.getType());
            }
            if (updateProduct.getPrice() != null) {
                findProduct.setPrice(updateProduct.getPrice());
            }
            System.out.println("The " + selectedProduct + " had been successfully updated!");
            return findProduct;
        }
        return null;
    }

    private Product createUpdateProductWithInformation(String selectedProduct) {
        System.out.println("\nUpdated Product information for " + selectedProduct + ".\n");
        System.out.println("Please provide the new Product Name: ");
        String newProductName = getScanner().next();
        System.out.println("Please provide the new Product Type: \n" + Arrays.toString(ProductType.values()));
        ProductType newProductType = ProductType.getProductType(getScanner().next());
        System.out.println("Please provide the new Price: ");
        BigDecimal newPrice = getScanner().nextBigDecimal();
        System.out.println("Please provide the new Description: ");
        String newDescription = getScanner().next();

        return new Product(newProductName, newPrice, newProductType, newDescription);
    }

    private Optional<Product> getProductFromCustomerCart(Customer customer, Product findProduct) {
        List<Product> customerCartProductList = customerCartProductList(customer);
        if (!customerCartProductList.isEmpty()) {
            return customerCartProductList.stream()
                    .filter(product -> product.equals(findProduct))
                    .findFirst();
        }
        return Optional.empty();
    }

    private List<Product> customerCartProductList(Customer customer) {
        return customer.getCart().getListOfProducts();
    }

    public Product findProduct(String productName) {
        if (!getProducts().isEmpty()) {
            for(Product productKey: getProducts().keySet()) {
                if (productName != null) {
                    if (productKey.getName().equals(productName)) {
                        return productKey;
                    }
                }
            }
        }
        return null;
    }

    public Product findProduct(Product product) {
        if (!getProducts().isEmpty()) {
            for(Product productKey: getProducts().keySet()) {
                if (product.getName() != null) {
                    if (productKey.getName().equals(product.getName())) {
                        return productKey;
                    }
                }
            }
        }
        return product;
    }

    public boolean isProductAvailability(Product product) {
        if (product != null) {
            return getProducts().get(product) > 0;
        } else {
            return false;
        }
    }

    public void getListOfProducts() {
        for(Map.Entry<Product, Integer> product: getProducts().entrySet()) {
            System.out.println(product.getKey() + " Amount of the product: " + product.getValue());
        }
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
