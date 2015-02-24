# Cash Register Problem

Inspired from https://gist.github.com/searls/1202077

A cash register has a drawer with separate bins for eight different denominations of money:

* Pennies
* Nickels
* Dimes
* Quarters
* Singles
* Fives
* Tens
* Twenties

From time to time, a customer will arrive with a cart full of merchandise, and tender a cash payment for the merchandise, also using the above eight denominations of money.

At this point, the cashier is faced with one of three possible situations:

1. The customer may not have tendered enough money to pay for the merchandise.
2. The limitations of the cash in the register may prevent making appropriate change.
3. The transaction may proceed normally and successfully.

Using Test-Driven Development and Object-Oriented Programming, write a method (perhaps on a "CashRegister" object) that accepts a merchandise value and a payment in up to eight denominations, and returns a value from which the unit test can tell which of the above three situations apply.

Once you've finished, think about what changes you'd have to make to your program to support fifty-cent pieces and two-dollar bills as well.

Then consider what would happen if your cash register had to support several transactions in a row, where some of the ones in the middle could be fail-to-make-change transactions. (Depending on how you write your code, this could require a fairly involved modification to enable undoing a partially-completed transaction when it becomes clear that completing it is impossible.)

## Extra Credit!

There's an obvious standard change-making algorithm to use for this problem. However, Tracy Harms, one of our regular Code Retreaters, noticed a significant problem with this standard algorithm.

Here's an example. Suppose you have to make fifty cents' worth of change, and your cash register contains only one quarter and five dimes. You can absolutely make fifty cents of change with five dimes. But with the standard algorithm, you'll do this:

1. Take the quarter; now you have to make twenty-five more cents of change.
2. Take two dimes; now you have to make five more cents of change.
  * No nickels.
  * No pennies.
3. No more denominations, but there's still change to make. Can't make change!

Once you've got that standard algorithm working, write a test for the quarter-and-five-dimes case, watch it fail, and see if you can refactor the algorithm to make it pass. Specifically, consider a recursive algorithm.
Note: Fifty-cent pieces should work fine; but if you extended your solution to handle two-dollar bills, this same phenomenon will appear at that point in the denomination list. For example, making six dollars of change with one five and three twos in the register will fail when it should succeed.