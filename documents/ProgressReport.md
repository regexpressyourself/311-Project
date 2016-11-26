Sam Messina

Ganesh Koripali

Mohammed Abdulkadir

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">Assigned</th>
<th scope="col" class="org-left">Todo (0%)</th>
<th scope="col" class="org-left">Doing (0% - 100%)</th>
<th scope="col" class="org-left">Done (100%)</th>
</tr>
</thead>

<tbody>
<tr>
<td class="org-left">Sam, Ganesh, Mohammed</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">BNF</td>
</tr>


<tr>
<td class="org-left">Sam, Ganesh, Mohammed</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">Denotational Semantics</td>
</tr>


<tr>
<td class="org-left">Sam</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">Create Parser</td>
</tr>


<tr>
<td class="org-left">Mohommed, Sam</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">Create Lexical Analyzer (20% done)</td>
<td class="org-left">&#xa0;</td>
</tr>


<tr>
<td class="org-left">Ganesh</td>
<td class="org-left">&#xa0;</td>
<td class="org-left">Create Pseudo-compiler)</td>
<td class="org-left">&#xa0;</td>
</tr>
</tbody>
</table>


# Todo specifics


## Create BNF

Come up with syntax for our language and write BNF/EBNF to specify it. Language should support all queue functions, including:

-   [X] line
-   [X] expression
-   [X] Add
-   [X] Remove
-   [X] Peek
-   [X] Get Length
-   [X] Is Empty
-   [X] Not Empty
-   [X] view
-   [X] If
-   [X] element


## Create Denotational Semantics

Write a set of denotaional semantics detailing semantics of queue creation and function calls


## Create Parser

Create a parser that will run through source code and return a list of all valid tokens found. Parser will:

-   [X] Take in the source code of the program
-   [X] Extracts Tokens
-   [X] Return an array of tokens for the program


## Create Lexical Analyzer

Create a lexical analyzer that:

-   [X] Takes its input from our parser
-   [ ] Checks the syntax against our BNF
-   [ ] Checks semantics against denotational semantic rules
-   [ ] Evaluates action to be taken
-   [ ] Passes instructions to compiler to preform actions


## Create Pseudo Compiler

Runs commands as specified by the lexical analyzer. This includes:

-   [X] Maintain state of program while lexical analyzer is working
-   [ ] Take instructions from analyzer to adjust state and the queue
-   [X] Preform instructions using Java
-   [X] Run program

