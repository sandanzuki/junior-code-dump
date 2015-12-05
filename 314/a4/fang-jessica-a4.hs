-- Assignment 4, CSCE-314
-- Section: 502
-- Student Name:  Jessica Fang
-- UIN: 224003796
-- Kyra (Katherine) Drake, Colin Banigan, Steven Bierwagen, Alexandria Stacy

module Main where

import Prelude hiding (lookup)

import Test.HUnit
import System.Exit

-- AST definition for W
data WValue = VInt Int 
            | VBool Bool 
              deriving (Eq, Show)

data WExp = Val WValue
          | Var String

          | Plus WExp WExp
          | Minus WExp WExp
          | Multiplies WExp WExp
          | Divides WExp WExp

          | Equals WExp WExp
          | NotEqual WExp WExp
          | Less WExp WExp
          | Greater WExp WExp
          | LessOrEqual WExp WExp
          | GreaterOrEqual WExp WExp

          | And WExp WExp
          | Or WExp WExp
          | Not WExp

data WStmt = Empty
           | VarDecl String WExp
           | Assign String WExp
           | If WExp WStmt WStmt
           | While WExp WStmt
           | Block [WStmt]

type Memory = [(String, WValue)]
marker = ("|", undefined)
isMarker (x, _) = x == "|"

-- eval function
eval :: WExp -> Memory -> WValue
eval (Val a) m = a
eval (Var a) m = case lookup a m of
    Nothing -> error ("nonexistant variable : " ++ a)
    Just v -> v

eval(Plus a b) m = let e1 = eval a m; e2 = eval b m in VInt $ asInt e1 + asInt e2
--eval(Plus _ _ ) m = error ("type mismatch")
eval(Minus a b) m = let e1 = eval a m; e2 = eval b m in VInt $ asInt e1 - asInt e2
eval(Multiplies a b) m = let e1 = eval a m; e2 = eval b m in VInt $ asInt e1 * asInt e2
eval(Divides a b) m = let e1 = eval a m; e2 = eval b m in VInt $ asInt e1 `div` asInt e2
eval(Equals a b) m = let e1 = eval a m; e2 = eval b m in VBool $ e1 == e2
eval(NotEqual a b) m = let e1 = eval a m; e2 = eval b m in VBool $ e1 /= e2
eval(Less a b) m = let e1 = eval a m; e2 = eval b m in VBool $ asInt e1 < asInt e2
eval(Greater a b) m = let e1 = eval a m; e2 = eval b m in VBool $ asInt e1 > asInt e2
eval(GreaterOrEqual a b) m = let e1 = eval a m; e2 = eval b m in VBool $ asInt e1 >= asInt e2
eval(LessOrEqual a b)m = let e1 = eval a m; e2 = eval b m in VBool $ asInt e1 <= asInt e2
eval(And a b) m = let e1 = eval a m; e2 = eval b m in VBool $ asBool e1 && asBool e2
eval(Or a b) m = let e1 = eval a m; e2 = eval b m in VBool $ asBool e1 || asBool e2
eval(Not a) m = VBool $ not (asBool e1) where e1 = eval a m

-- exec function
exec :: WStmt -> Memory -> Memory
exec (Empty) m = m

exec (VarDecl s e) m = if (scopelookup s m == Nothing) then (s,(eval e m)):m else error (s ++ " already defined")

exec (Assign s e) m = replacefirst s (eval e m) m where 
    replacefirst _ _ [] = error $ "undefined variable : " ++ s
    replacefirst s v (n@(k,_):ns)
        | s == k = if (samev v n) then (k,v):ns else error $ "type mismatch" 
        | otherwise = n:replacefirst s v ns
    samev (VInt _) (_, VInt _) = True
    samev (VBool _) (_, VBool _) = True
    samev _ _ = False

exec (If e a b) m = if asBool(eval e m) then exec a m else exec b m

exec (While e s) m = if (asBool(eval e m)) then exec(While e s)(exec s m) else m

exec (Block ss) m = popmarker $ foldr exec(marker:m)(reverse ss) where
    popmarker [] = []
    popmarker(x:xs) 
        | isMarker x = xs
        | otherwise = popmarker xs

-- example programs
prog1 = Block
     [
       VarDecl "x" (Val (VInt 0)),
       VarDecl "b" (Greater (Var "x") (Val (VInt 0))),
       If (Or (Var "b") (Not (GreaterOrEqual (Var "x") (Val (VInt 0)))))
         ( Block [ Assign "x" (Val (VInt 1)) ] )
         ( Block [ Assign "x" (Val (VInt 2)) ] )
     ]

factorial = 
  Block
  [
    VarDecl "acc" (Val (VInt 1)),
    While (Greater (Var "arg") (Val (VInt 1)))
    (
      Block
      [
        Assign "acc" (Multiplies (Var "acc") (Var "arg")),
        Assign "arg" (Minus (Var "arg") (Val (VInt 1)))         
      ]
    ),
    Assign "result" (Var "acc")
  ]

fibonacci = 
  Block
  [
    VarDecl "counter" (Val (VInt 1)),
    VarDecl "temp" (Val (VInt 0)),
    VarDecl "nminus2" (Val (VInt 0)),
    VarDecl "nminus1" (Val (VInt 1)),
    While (Less (Var "counter") (Var "x"))
    (
      Block
      [
        Assign "temp" (Plus (Var "nminus2") (Var "nminus1")),
        Assign "nminus2" (Var "nminus1"),
        Assign "nminus1" (Var "temp"),
        Assign "counter" (Plus (Var "counter")(Val (VInt 1)))
      ]
    ),
    
    If(Equals(Var "x")(Val (VInt 1)))
      (Block[Assign "result" (Var "nminus1")])
      (Block[Assign "result" (Var "temp")])
  ]
  
ifwhiletest = 
  Block
  [
    VarDecl "a" (Val (VInt 10)),
    VarDecl "b" (Val (VInt 5)),
    VarDecl "c" (Multiplies(Var "a")(Var "b")), 
    VarDecl "d" (Plus(Divides(Var "c")(Var "b"))(Val (VInt 1))), 
    VarDecl "e" (Val (VInt 0)),
    VarDecl "counter" (Val (VInt 0)),
    
    While(Less(Var "counter")(Var "b"))
    (
      Block
      [
        Assign "counter" (Plus (Var "counter")(Val (VInt 1))),
        If(Equals(Var "d")(Plus(Var "a")(Val (VInt 1))))
            (Block[Assign "e" (Plus(Var "e")(Var "counter"))])
            (Block[Assign "e" (Val (VInt 2))]),
        Assign "a" (Val (VInt 0))
      ]
    ),
    
    
    Assign "result" (Var "e")
  ]


  
-- some useful helper functions
lookup s [] = Nothing
lookup s ((k,v):xs) 
    | s == k = Just v
    | otherwise = lookup s xs
                    
scopelookup s [] = Nothing
scopelookup s ((k,v):xs) 
    | isMarker (k,v) = Nothing
    | s == k = Just v
    | otherwise = scopelookup s xs

asInt (VInt v) = v
asInt x = error $ "expected a number, got " ++ show x

asBool (VBool v) = v
asBool x = error $ "expected a boolean, got " ++ show x

fromJust (Just v) = v
fromJust Nothing = error "expected a value in Maybe, but got Nothing"

-- W language
fibonacciW :: Int -> Int
fibonacciW x = asInt $ fromJust $ lookup "result" (exec fibonacci [("result", VInt (-1)), ("x", VInt x)])

-- unit tests
myTestList =
  TestList [
    test $ assertEqual "prog1 test" [] (exec prog1 []),
    
    test $ assertBool "plus" (20 == asInt(eval(Plus(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "minus" (0 == asInt(eval(Minus(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "multiplies" (100 == asInt(eval(Multiplies(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "divides" (1 == asInt(eval(Divides(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "stuff inside of stuff 1" (2 == asInt(eval(Divides(Plus(Var "x")(Var "y"))(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "stuff inside of stuff 2" (11 == asInt(eval(Plus(Var "x")(Divides(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "stuff inside of stuff 3" ((-80) == asInt(eval(Minus(Plus(Var "x")(Var "y"))(Multiplies(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "stuff inside of stuff 4" (0 == asInt(eval(Multiplies(Minus(Var "x")(Var "y"))(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "equals1" (True == asBool(eval(Equals(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "equals2" (False == asBool(eval(Equals(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [5,10])),
    test $ assertBool "equals3" (True == asBool(eval(Equals(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [True,True])),
    test $ assertBool "equals4" (False == asBool(eval(Equals(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [False,True])),
    test $ assertBool "equals5" (True == asBool(eval(Equals(Multiplies(Minus(Var "x")(Var "y"))(Divides(Var "x")(Var "y")))(Minus(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "not equal1" (False == asBool(eval(NotEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "not equal2" (True == asBool(eval(NotEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,5])),
    test $ assertBool "not equal3" (False == asBool(eval(NotEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [True,True])),
    test $ assertBool "not equal4" (True == asBool(eval(NotEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [False,True])),
    test $ assertBool "not equal5" (True == asBool(eval(NotEqual(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "less1" (True == asBool(eval(Less(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [5,10])),
    test $ assertBool "less2" (False == asBool(eval(Less(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "less3" (False == asBool(eval(Less(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,5])),
    test $ assertBool "less4" (False == asBool(eval(Less(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "greater1" (False == asBool(eval(Greater(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [5,10])),
    test $ assertBool "greater2" (False == asBool(eval(Greater(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "greater3" (True == asBool(eval(Greater(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,5])),
    test $ assertBool "greater4" (True == asBool(eval(Greater(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "lessorequal1" (True == asBool(eval(LessOrEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [5,10])),
    test $ assertBool "lessorequal2" (True == asBool(eval(LessOrEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "lessorequal3" (False == asBool(eval(LessOrEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,5])),
    test $ assertBool "lessorequal4" (False == asBool(eval(LessOrEqual(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "greaterorequal1" (False == asBool(eval(GreaterOrEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [5,10])),
    test $ assertBool "greaterorequal2" (True == asBool(eval(GreaterOrEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "greaterorequal3" (True == asBool(eval(GreaterOrEqual(Var "x")(Var "y")) $ zip ["x", "y"] $ map VInt [10,5])),
    test $ assertBool "greaterorequal4" (True == asBool(eval(GreaterOrEqual(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "and1" (True == asBool(eval(And(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [True,True])),
    test $ assertBool "and2" (False == asBool(eval(And(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [True,False])),
    test $ assertBool "and3" (False == asBool(eval(And(GreaterOrEqual(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y")))(Less(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "or1" (True == asBool(eval(Or(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [True,True])),
    test $ assertBool "or2" (True == asBool(eval(Or(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [True,False])),
    test $ assertBool "or3" (False == asBool(eval(Or(Var "x")(Var "y")) $ zip ["x", "y"] $ map VBool [False,False])),
    test $ assertBool "or4" (True == asBool(eval(Or(GreaterOrEqual(Divides(Multiplies(Var "x")(Var "y"))(Plus(Var "x")(Var "y")))(Minus(Var "x")(Var "y")))(Less(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    test $ assertBool "not1" (True == asBool(eval(Not(Var "x")) $ zip ["x", "y"] $ map VBool [False,True])),
    test $ assertBool "not2" (False == asBool(eval(Not(Var "y")) $ zip ["x", "y"] $ map VBool [False,True])),
    test $ assertBool "not3" (True == asBool(eval(Not(Less(Var "x")(Var "y"))) $ zip ["x", "y"] $ map VInt [10,10])),
    
    let temp = lookup "result" (exec ifwhiletest [("result", VInt (-1))])
    in test $ assertBool "ifwhiletest" (2 == asInt (fromJust temp)),
    
    let res = lookup "result" (exec factorial [("result", VInt (-1)), ("arg", VInt 10)])
    in test $ assertBool "factorial of 10" (3628800 == asInt (fromJust res)),
    
    let temp1 = lookup "result" (exec fibonacci [("result", VInt (-1)), ("x", VInt 10)])
    in test $ assertBool "fibonacci 10" (55 == asInt (fromJust temp1)),
    
    test $ assertBool "fibonacciw 50" (12586269025 == fibonacciW 50)
    ]    

-- main: run the unit tests  
main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          if (errs + fails /= 0) then exitFailure else return ()

