    package se.kth.iv1350.pointOfSale.DTO;

    import se.kth.iv1350.pointOfSale.model.Item;
    /**
    *  ItemDTO is representing the transferable item object that consists of: 
    *  ID, Name, Price, Description, quantity and its VAT.
    */
    public class ItemDTO {
        private String ID;
        private String name;
        private double price;
        private String description;
        private int quantity;
        private double VAT;

        /**
         * Constructor that initialize fields with provided values
         * @param itemID is the ID of the item
         * @param name is the name of the item
         * @param price is the price of the item
         * @param description is the description of the item
         * @param quantity is the specified quantity of the item, that is, how many there is of an item
         * @param VAT is how much VAT (tax rate) the item have. 
         *  */
        
        public ItemDTO(String itemID, String name, double price, String description, int quantity, int VAT){
            this.ID = itemID;
            this.name = name;
            this.price = price;
            this.description = description;
            this.quantity = quantity;
            this.VAT = VAT;
        }

    /**
     * Constructor that creates the fiels for the objects belove
     * @param item is the object that is being created
     */
        public ItemDTO(Item item){
            this.ID = item.ID;
            this.name = item.name;
            this.price = item.price;
            this.description = item.description;
            this.quantity = item.quantity;
            this.VAT = item.VAT;
        }

    /**
     * Getter methods that returns the ItemDTO's properties:
     * ID, Name, Price, Description, VAT and Quantity.
     *  
     */ 
        public String getID() {
            return ID;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }

        public double getVAT(){
            return VAT;
        }

        public int getQuantity(){
            return quantity;
        }

    }
