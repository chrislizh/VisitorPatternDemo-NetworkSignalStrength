# VisitorPatternDemo-NetworkSignalStrength
Visitor Design Pattern lets you define a new operation without changing the classes of the elements on which the visitor class operates.
This example shows how to use Visitor Pattern to solve a network signal strength retrieval problem, which not only achieves concern separation via loose coupling but also successfully avoids using a series of If-Else and isInstance() clauses if we solve the problem in traditional way.

This project demostrates the advantages of using Visitor Pattern along with Java Generics concept, which make the task of adding new algorithms to the existing module become incredibly easy. 
I.e., if we need to define a new algorithm to handle a new CellInfo subclass, we just need to create a new "actual visitor" class with the algorithm and modify function getActualVisitor() of CellInfoVisitor class to return a proper CellInfo instance.