**ADDING AN ITEM**

https://www.youtube.com/watch?v=9xflCDe-Ruw

Make sure that the following files are present on your used build of the mod
-   item/ModItems.java file
-   lang/en_us.json
-   models/item directory
-   textures/item directory


When all the files have been located place the following lines in each given funtion

**item/ModItems**
-  Copy the following line of code and change it according to your prefrences:

   public static final Item ITEM_CAPITALIZED = registerItem("item_lowercased", new Item(new Item.Settings()));
   -  ITEM_CAPITALIZED is the item name in all capitalized letters and _ for spaces
   -  item_lowercased is the item name in all lowercased letters and _ for spaces
-  Paste the completed line of code in: public class ModItems{

-  An exapmle line would be:

   public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));

**lang/en_us.json**
-  Copy the following line of code and change it according to your prefrences:
   "item.permafrostpeaks.item_lowercased": "Item Display Name",
   -  item_lowercased is the same name that was used in item/ModItems.java
   -  Item Display Name is the name that will be displayed when the player holds the item or hovers over it

-  An example line would be:

   "item.permafrostpeaks.pink_garnet": "Pink Garnet",
   
**models/item**
-  In this directory you have to add a new file named as followed:

   item_lowercased.json
   -  The item_lowercased being the same name used in item/ModItems.java
-  An example of this would be:

   pink_garnet.json
-  In this file you have to paste the following and change it to your item:
  {
    "parent": "minecraft:item/generated",
    "textures": {
      "layer0": "permafrostpeaks:item/item_lowercased"
    }
  }
   -  The item_lowercased once again being the one also used in item/ModItems.java
-  An example of this would be:
  {
    "parent": "minecraft:item/generated",
    "textures": {
      "layer0": "permafrostpeaks:item/pink_garnet"
    }
  }

**textures/item**
-  All that you haver to do in this file is add the .png file to the directory
-  MAKE SURE THAT THE FILE IS .png OTHERWISE THE TEXTURE WON'T REGISTER


