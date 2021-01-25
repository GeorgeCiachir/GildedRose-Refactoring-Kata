## GildedRose-Refactoring-Kata

### The kata has been forked from [this repository](https://github.com/emilybache/GildedRose-Refactoring-Kata)
* This implementation represents a possible refactoring solution in Java
* For technical details about testing and running the kata, please take a look at this [README.md file](README.md) 
 
### General idea
* GildedRose is a store that trades several products
* For these products, the quality updates based on some rules
* The original implementation for how those rules apply wasn't very... let's say flexible, 
when it came to adding new rules for new custom products, so the store had a difficult time
expanding its offer
* The purpose of this kata is to facilitate easier assimilation of new products, without 
affecting the outcome of the current update rules

### Specifications
Some of these specifications have been extracted from the original text (found at the above mentioned url), while
others have been deduced from the existing code

##### The Normal items
* These include: **+5 Dexterity Vest** and **Elixir of the Mongoose**
* Sell date decreases by 1 at the end fo each day
* Quality: minimum 0, maximum 50
* Decreases in quality
* Before the sell date, quality degradation step = 1
* After the sell date, quality degradation step = 2

##### Aged Brie
* Sell date decreases by 1 at the end fo each day
* Quality: minimum 0, maximum 50
* Increases in quality
* Before the sell date, quality increase step = 1
* After the sell date, quality increase step = 2

##### Backstage passes
* Sell date decreases by 1 at the end fo each day
* Quality: minimum 0, maximum 50
* Increases in quality
* Before ten days to the sell date, quality increase step = 1
* After ten days to the sell date, quality increase step = 2
* After five days to the sell date, quality increase step = 3

##### Sulfuras
* Never ages -> Sell date fixed
* Quality: fixed to 80

##### Conjured
* Sell date decreases by 1 at the end fo each day
* Quality: minimum 0, maximum 50
* Decreases in quality
* Before the sell date, quality degradation step = 2
* After the sell date, quality degradation step = 4
