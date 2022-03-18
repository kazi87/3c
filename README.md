# 3c App (Charlene's Coffee Corner)

A simple program accepting list of products and generating a receipt. Full specification can be found [here](SPEC.MD)

# Thinking process / decision records

For better understanding, I will provide some decision records in a form of paragraphs in this section

## Java version

There are many great features introduced by the recent java releases, but i will use Java `11` as this is the version
which I use on daily basis.

## Price representation

I will use the Java `BigDecimal` type to represent price values. This is one of the most accurate ways to represent this
kind of values. A `HALF_UP` rounding will provide a format which is globally accepted and used. 

## Extras can be mixed with each other and duplicated

I assume that extras are given for an Offering and can be selected independently (e.g. client can ask for `Extra Milk`
and `Foamed Milk` at the same time). They also can be duplicated (e.g. a Client may ask for two `Extra Milk`s ).
The only validation i do for Extras is `Type` verification - user can order Extra only for "compatible" offerings. For this purpose i've introduced a `Type` class  

## Model

From the descriptions i've extracted the following domains objects:

* `Order` / `OrderItem` - representing an input data (order coming from a client)
* `Offering` / `Extra` - representing an available inventory
* `Pricing` / `PricePolicy` - responsible for calculating prices

## Input
For simplicity and to avoid parsing issues, i've assumed input data is represented as a list of ids (numerical values).
Comma separated ids defines respectively: `offeringId`,`extra1Id`,`extra2Id`,... (as many extras as needed).

In order to preview the Offerings and extras, please run the app without any parameters. A "Menu" will be displayed 

## Output

Receipt is generated as a simple output providing basic information about order (selected offerings and extras). Additionally `date`/`time` and unique `order ID` is generated for each order.

# Build

run `maven clean install` in order to build an exacutable `run.jar` artefact.

# Run

run `java -jar target/run.jar` after generating executable jar in order to prompt the help screen / menu
run `java -jar target/run.jar "1,1,3" "4"` after generating executable jar in order generate a valid recipe
run `java -jar target/run.jar "4,1,2"` after generating executable jar in order trigger input error - "Incompatible Extra" 



