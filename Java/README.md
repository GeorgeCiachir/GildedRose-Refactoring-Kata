## GildedRose-Refactoring-Kata

### The kata has been forked from [this repository](https://github.com/emilybache/GildedRose-Refactoring-Kata)
* This implementation represents a possible refactoring solution in Java
* For additional details about the Kata itself, please visit the original repo, or you could also take a 
look at this [DESCRIPTION.md file](DESCRIPTION.md) 
  
### Project
* This has been left for as a Maven project for easier packaging and testing

#### Prerequisites
* To run this project you need to have **maven 3+** and **Java8** installed

#### Compiling, running the project
* Command ``mvn clean install`` in the parent folder will compile, build and run the tests
* After this, cd into the target folder
* Command ``java -jar gilded-rose-kata-0.0.1-SNAPSHOT.jar {days}`` will now run the produced artefact with a 
specified number of days (int) to update the products. 
    This value defaults to 3.
    For example: ``java -jar gilded-rose-kata-0.0.1-SNAPSHOT.jar 10``

#### Testing
* I have purposely chosen to keep the original repository format, so that text testing can be used, 
as detailed in the original repository [here](https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/master/texttests)
* For this purpose, the TexttestFixture class has been kept in the test sources
* The implementations are covered by unit tests and I also expanded on the original text tests, 
to have a solid base while refactoring. For this approach I created a TextTests class which writes the product evolution
in an "evolution.txt" file and compares it to a "benchmark.txt" file. The benchmark file is based on the original output 
of the TexttestFixture class.