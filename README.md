# junior-code-dump

    Current as of January 22, 2016

# fall '15 / spring '16

    All the code included in this repository is coursework. 
    It has been uploaded to serve as portfolio to showcase my coding skill. 
    "An Aggie does not lie, cheat or steal or tolerate those who do."

## 312 - 

#### bomb lab. 

diffuse the assembly bomb.

#### final project. 

instruction set architecture in logisim.

## 313 - 

#### machine problem 1.

a high performance linked list.

## 314 - 

    WARNING: the code in this folder contains extremely bad programming practice due to required use of badly written skeleton code / code framework provided by the teacher.

#### a2.

haskell, practice with recursion.

#### a3.

haskell, practice with list comprehension, recursion, higher order functions, and data types.

#### a4.

haskell, interpreter for custom programming language, "w".

#### a5. 

haskell, parser for language from a4.

#### a6.

java, practice with inheriting classes and dynamic dispatching.

#### a7.

java, practice with java generics and implementing interfaces.

#### a8.

java, practice with multithreading and synchronization.

## 315 - 

#### achievement tracker. 

an application that keeps track of the relations between video games, players, and achievements. similar to [steam](http://store.steampowered.com/).

##### thoughts and discussion.

[(de)normalization of data](https://en.wikipedia.org/wiki/Denormalization/). normalization of data guarantees write optimization and consistency; however, reading data can be more complex and require multiple read operations. in contrast, denormalization of data improves read efficiency as well as scalability by storing redundant data; however, write operations become more complex as it is important that all data is kept in sync.

due to the types of operations used in the achievement tracker, i have opted for read optimization over write optimization therefore this implementation features denormalized data.
