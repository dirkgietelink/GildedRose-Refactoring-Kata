# Solution Guilded Rose Kata

### Introduction

For a solution I did not go for the traditional TDD Kent-Beck style, because incremental test-code-refactor loop, was 
not a wise starting point at the beginning. The code was too messy to understand, and the provided requirements were at 
some points too vague to give exclusive clarity.
Therefore I started writing a bunch unit tests first, before even touching the production code. After having implemented 
unit tests for all requirements (tests for unimplemented conjured items disabled), I started refactoring. This would 
cause breaking tests, from which I started a more standard TDD loop.

### Issues fixed

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

### Assumptions

* Aged Brie is not decreasing in quality, once the due date has passed (this is a bit ambiguous from the requirements).
* Sulfuras sellIn value is set to maximum int value after the first update, because the spec states that it never has to be sold. 
* The way item names are matched is unclear. The specs and existing implementation are kind of ambiguous. 
Therefore, I introduced a configuration called strictNameMatcher. 
The default is true, but it can be set to false via app.setSstrictNameMatcher(false). For details, see the javadoc.

### How I progressed through the refactoring

* Forked the project to my own Github
* Read  requirements and tried to understand the code from a global level
* Ran the tests
* Formalized the requirements description,in order to create a baseline for adding new unit tests.
* Added a number of unit tests to meet all the requirements. 
* Disabled some tests as they were failing and could not be implemented yet (Sulfuras quality, and Conjured items).
* All remaining tests were now green, which is good starting point for refactoring.
* Extracted constants
* Splitted updateQuality into different methods for each type of Item
* Fixed new code, based on unit tests
* Removed old messy code
* Created ItemUpdater interface, which is implemented by Decorator classes for updating the state of specific items.
* Refactored those classes by implementing default method and sensible constants.
* Implemented factory pattern for creating the specific type of ItemUpdater.
* Refactored the GildedRose application to use the factory. 
* Code is in acceptable refactored state, so I enabled the tests for conjured items and implemented its functionality.
* For further optimizations look at the git log of my  forked repo on Github.

### Github reference
[Gilded Rose fork on Github dirkgietelink](https://github.com/dirkgietelink/GildedRose-Refactoring-Kata)