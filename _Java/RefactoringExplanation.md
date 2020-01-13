# Solution Guilded Rose Kata

### Introduction

For a solution I did not go for the traditional TDD Kent-Beck style, because incremental test-code-refactor loop, was 
not a wise starting point at the beginning. The code was too messy to understand, and the provided requirements were at 
some points too vague to give exclusive clarity.
Therefore I started writing a bunch unit tests first, before even touching the production code. After having implemented 
unit tests for all requirements (tests for unimplemented conjured items disabled), I started refactoring. This would 
cause breaking tests, from which I started a more standard TDD loop.

* issues to fix
* how I tackled the kata
* assumptions
* bugs
* layout and explanation of the solution code

### Issues to fix

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

### Assumptions

* Aged Brie is not decreasing in quality, once the due date has passed (this is a bit ambiguous from the requirements).
* Aged Brie is matched ignoring case sensitivity.
* Aged Brie is matched ignoring leading or trailing spaces.
* Aged Brie is also matched if the Item name contains other words.

### How I progressed through the refactoring

* Forked the project to my own  Github
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
* 
*
* Once code is in acceptable refactored state, will add the conjured item functionality.

### Bugs found and fixed

### Github reference
[My Guilded Rose fork on Github](https://github.com/dirkgietelink/GildedRose-Refactoring-Kata)