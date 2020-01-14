# Solution Gilded Rose Kata

### Introduction

For a solution I did not go for the traditional TDD Kent-Beck style, because incremental test-code-refactor loop, was 
not a wise starting point at the beginning. The code was too messy to understand, and the provided requirements were at 
some points too vague to give exclusive clarity.

Therefore I started writing a bunch unit tests first, before even touching the production code. After having implemented 
unit tests for all requirements (tests for unimplemented conjured items disabled), I started refactoring. This would 
cause breaking tests, from which I started a more standard TDD loop.

### Checklist for issues to fix

In random order, this is an overview of the things that has to be taken care of to complete this assignemnt:

* magic numbers
* hardcoded strings
* duplicated (`if` and `else`) statements
* handle different types of items
* polymorphism
* open-closed principle: make it extendable, not modifiable
* remove unused code
* nullpointer checks
* javadoc
* create README or assisting documentation of my solution
* robustness (based on assumptions)
* clean up code: commented code, todos, and disabled tests
* verify code coverage
* package structure
* checkstyle

### Assumptions

After analyzing the original code and the requirements specification, I still found some ambiguity. Based on this I
recorded some assumptions as written down below.

* Aged Brie is not decreasing in quality, once the due date has passed (this is a bit ambiguous from the requirements).
* Sulfuras sellIn value is set to maximum int value after the first update, because the spec states that it never has to be sold. 
* The way item names are matched is unclear. The specs and existing implementation are kind of ambiguous. 
Therefore, I introduced a configuration called strictNameMatcher. 
The default is true, but it can be set to false via app.setSstrictNameMatcher(false). For details, see the javadoc.
* Nothing was stated in the specs or implemented in the original version for exception handling. Therefore, I assumed a 
fail-fast approach, by throwing a descriptive RuntimeException when a null Item or Item with null name is encountered.
The exceptions are NOT caught in the application, and are expected to bubble up to the caller of the application.

### How I progressed through the refactoring assignment

Here is how I went through the process of doing this assignment.

* Forked the project to my own Github.
* Read  requirements and tried to understand the code from a global level.
* Build the project, and ran the tests.
* Formalized the requirements description, in order to create a baseline for adding new unit tests.
* Added a number of unit tests to meet all the requirements. 
* Disabled some tests as they were failing and could not be implemented yet (Sulfuras quality, and Conjured items).
* All remaining tests were now green, which is good starting point for refactoring.
* Extracted constants.
* Splitted updateQuality into different methods for each type of Item.
* Fixed new code, based on unit tests.
* Removed old messy code.
* Created ItemUpdater interface, which is implemented by Decorator classes for updating the state of specific items.
* Refactored those classes by implementing default method and sensible constants.
* Implemented factory pattern for creating the specific type of ItemUpdater.
* Refactored the GildedRose application to use the factory. 
* Code is in acceptable refactored state, so I enabled the tests for conjured items and implemented its functionality.
* Improved javadoc and other documentation.
* Implemented nullpointer checks and corresponding unit tests.
* For further optimizations look at the git log of my forked repo on Github (see "References" section): `git log --oneline`.

### Implementation details and project layout

Here are some notes on the implementation.

* The project can be found in sub folder `_Java`. All other subfolders (related to other programming languages) have 
been removed in sake of clarity.
* The project is developed using JDK 1.8.0_191 (some Java 8 featureswere used like lambdas and default interface methods).
* The project is built and tested using **Maven**. I did update `build.gradle` as well, but it is untested!
* Project can be built and tested with `mvn clean install`.
* Lombok has been added to the list of dependencies, for removing boilerplate code of constructors and getters and setters.
* There is one test class `com.gildedrose.GildedRoseTest`, which covers most of the functionality.
* Package `com.gildedrose` contains the original classes `Item` and `GildedRose`. The latter is the starting point of the app.
* Package `com.gildedrose.updater` contains classes and interfaces related behaviour for updating the Item state, and wrapping the Items.
* Package `com.gildedrose.exception` contains a runtime exception, that can be produced in case of invalid input. 
This exception is uncaught due to lacking requirements. See also section "Assumptions".

### References

* [Gilded Rose fork on Github of dirkgietelink](https://github.com/dirkgietelink/GildedRose-Refactoring-Kata)