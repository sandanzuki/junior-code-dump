-- Assignment 2, CSCE-314
-- Section: 502
-- Student Name: Jessica Fang
-- UIN: 224003796
-- Justin Gonzalez. Jonathan Grimes. Colin Banigan.

module Main where

import Test.HUnit
import System.Exit

factorial :: Int -> Int
factorial 0 = 1
factorial 1 = 1
factorial n = n * factorial(n-1)

fibonacci :: Int -> Int
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = fibonacci(n-1) + fibonacci(n-2)

mySum :: [Int] -> Int
mySum [] = 0
mySum(n:ns) = n + mySum(ns)

flatten :: [[a]] -> [a]
flatten [] = []
flatten(x:xs) = [] ++ x ++ flatten xs

myLength :: [a] -> Int
myLength [] = 0
myLength(x:xs) = 1 + myLength(xs)

isElement :: Eq a => a -> [a] -> Bool
isElement a [] = False
isElement a (x:xs) = if a == x then True else isElement a xs
    
type Set a = [a]

mkSet :: Eq a => [a] -> Set a
mkSet [] = []
mkSet (x:xs) = if isElement x xs then mkSet xs else [x] ++ mkSet xs

size ::  Set a -> Int
size x = myLength(x)

subset :: Eq a => Set a -> Set a -> Bool 
subset [ ] a = True
subset (a:as) x = if (isElement a x) then subset as x else False

setEqual :: Eq a => Set a -> Set a -> Bool 
setEqual a x = if (subset a x && subset x a) then True else False

powerset :: Set a -> Set (Set a)
powerset [] = [[]]
powerset (x:xs) = powerset xs ++ f (x, powerset xs) where f (e, t) = [y ++ [e] | y <- t] 


myTestList = 
  TestList [ 
    "factorial" ~: factorial 3 ~=? 6
    
    , "fibonacci" ~: fibonacci 4 ~=? 3

    , "mySum" ~: mySum [1..10] ~=? 55
    
    , "flatten 1" ~: flatten [[]::[Int]] ~=? []
    , "flatten 2" ~: flatten [[]::[Int], [], []] ~=? [] 
    , "flatten 3" ~: flatten [[1], [2, 3, 4], [], [5, 6]] ~=? [1, 2, 3, 4, 5, 6]
    
    , "myLength" ~: myLength [1, 2, 3] ~=? 3
      
    , "isElement 1" ~: (isElement 'c' "abcd") ~=? True
    , "isElement 2" ~: (isElement 'e' "abcd") ~=? False
      
    , "mkSet 1" ~: size (mkSet "abcdaab") ~=? 4
    , "mkSet 2" ~: size (mkSet "abc") ~=? 3
      
    , "subset 1" ~: subset (mkSet "dbdb") (mkSet "abcdaab") ~=? True
    , "subset 2" ~: subset (mkSet "abc") (mkSet "bcccbhk") ~=? False
      
    , "setEqual 1" ~: setEqual (mkSet "abc") (mkSet "abcf") ~=? False
    , "setEqual 2" ~: setEqual (mkSet "abccbac") (mkSet "cbabc") ~=? True
      
    , "powerset 1" ~: size (powerset (mkSet []::[Int])) ~=? 1
    , "powerset 2" ~: size (powerset (mkSet [1])) ~=? 2
    , "powerset 3" ~: size (powerset (mkSet [1, 2])) ~=? 4
    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess