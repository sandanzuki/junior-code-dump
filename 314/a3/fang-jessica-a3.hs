-- Assignment 3, CSCE-314
-- Section: 502
-- Student Name: Jessica Fang
-- UIN: 224003796
-- Colin Banigan, Daniel Latypov, Maisam Arif, Jonathan Grimes
-- run in terminal: ghci fang-jessica-a3.hs; enter "main" into ghci

module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char

triads :: Int -> [(Int, Int, Int)]
triads x = [(a,b,c) | b <- [1..x], a <- [1..b], c <- [1..x], a^2 + b^2 == c^2]

perfect :: [Int]
perfect = [n | n <- [1..], (sum[x | x <- [1..n], mod n x == 0] - n) == n]

mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]
mergeBy op [] [] = []
mergeBy op xs [] = xs
mergeBy op [] ys = ys
mergeBy op (x:xs) (y:ys) = if op x y then x : mergeBy op xs (y:ys) else y : mergeBy op (x:xs) ys

split :: [a] -> ([a],[a])
split (x:y:zs) = let (xs,ys) = split zs in (x:xs,y:ys)
split [x]      = ([x],[])
split []       = ([],[])

mergeSortBy :: (a -> a -> Bool) -> [a] -> [a]
mergeSortBy op [] = []
mergeSortBy op [x] = [x]
mergeSortBy op xs = mergeBy op (mergeSortBy op as) (mergeSortBy op bs) where (as, bs) = split xs

mergeSort :: Ord a => [a] -> [a]
mergeSort [] = []
mergeSort [x] = [x]
mergeSort xs = mergeBy (<=) (mergeSort as) (mergeSort bs) where (as, bs) = split xs

multiply :: [Int] -> Int
multiply a = foldr (*) 1 a

concatenate :: [String] -> String     
concatenate x = foldl (++) [] x

concatenateAndUpcaseOddLengthStrings :: [String] -> String
concatenateAndUpcaseOddLengthStrings [] = ""
concatenateAndUpcaseOddLengthStrings (x:xs)
  |odd(length x) = map toUpper x ++ concatenateAndUpcaseOddLengthStrings xs
  |otherwise = concatenateAndUpcaseOddLengthStrings xs
-- implementation without higher order function :
--concatenateAndUpcaseOddLengthStrings [] = ""
--concatenateAndUpcaseOddLengthStrings (x:xs) = concatenate (if mod (length x) 2 == 1 then [[toUpper y | y <- x]] else []) ++ concatenateAndUpcaseOddLengthStrings xs 

insertionSort :: Ord a => [a] -> [a]
insertionSort = foldr insert []

maxElementOfAList :: Ord a => [a] -> a 
maxElementOfAList a = foldr1 max a

keepInBetween :: Int -> Int -> [Int] -> [Int]
keepInBetween a b xs = filter (<= b) ys where ys = filter (>= a) xs
-- implementation without higher order function :
--keepInBetween a b [] = []
--keepInBetween a b xs = [n | n <- xs, n >= a, n <= b]

data Tree a b = Branch b (Tree a b) (Tree a b)
              | Leaf a

instance (Show a, Show b) => Show (Tree a b) where show t = showIndent "  " t where
showIndent indent (Leaf x) = indent ++ show x; showIndent indent (Branch n l r) = indent ++ (show n) ++ "\n" ++ (showIndent indentmore l) ++ "\n" ++ (showIndent indentmore r) where indentmore = indent ++ "   "

preorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]
postorder :: (a -> c) -> (b -> c) -> Tree a b -> [c]
inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c]

preorder a b (Leaf n) = [x] where x = a n
preorder a b (Branch n l r) = [x] ++ y ++ z where x = b n; y = preorder a b l; z = preorder a b r

postorder a b (Leaf n) = [x] where x = a n
postorder a b (Branch n l r) = x ++ y ++ [z] where x = postorder a b l; y = postorder a b r; z = b n

inorder a b (Leaf n) = [x] where x = a n
inorder a b (Branch n l r) = x ++ [y] ++ z where x = inorder a b l; y = b n; z = inorder a b r

data E = IntLit Int
       | BoolLit Bool
       | Plus E E
       | Minus E E
       | Multiplies E E
       | Divides E E
       | Equals E E
         deriving (Eq, Show)

eval :: E -> E
eval (IntLit i) = IntLit i
eval (Plus a b) = IntLit $ asInt (eval a) + asInt (eval b)
eval (Minus a b) = IntLit $ asInt (eval a) - asInt (eval b)
eval (Multiplies a b) = IntLit $ asInt (eval a) * asInt (eval b)
eval (Divides a b) = IntLit $ asInt (eval a) `div` asInt (eval b)

eval (BoolLit n) = BoolLit n
eval (Equals a b) = BoolLit $ eval a == eval b


asInt :: E -> Int
asInt (IntLit i) = i

mytree = Branch "A" 
           (Branch "B" 
              (Leaf 1) 
              (Leaf 2)) 
           (Leaf 3)

program = Equals 
            (Plus (IntLit 1) (IntLit 2))
            (Minus
             (IntLit 5)
             (Minus (IntLit 3) (IntLit 1)))
    
myTestList =

  let te s e a = test $ assertEqual s e a
      tb s b = test $ assertBool s b

  in
    TestList [ 
        tb "triads 1" $ [(3, 4, 5)] == triads 5
      , tb "triads 2" $ [(3, 4, 5), (6, 8, 10)] == ( sort $ triads 10)

      , tb "perfect" $ take 3 perfect == [6, 28, 496]
        
      , te "mergeSort 1" " adhllowy" (mergeSort "howdy all") 
      , te "mergeSort 2" "" (mergeSort "") 
      , te "mergeSort 3" "x" (mergeSort "x") 
        
      , te "mergeSortBy 1" " 'eggim" (mergeSortBy (<) "gig 'em") 
      , te "mergeSortBy 2" "nmlkieecbbaJ  " (mergeSortBy (>) "Jack be nimble")
      , te "mergeSortBy 3" "" (mergeSortBy (<) "")

      , te "multiply" 10 (multiply [-2, -1, 5])
      , te "concatenate" "ABCD" (concatenate ["AB", "", "", "C", "D", ""])

      , te "concatenateAndUpcaseOddLengthStrings"
          "HERE'S AN EXAMPLE" (concatenateAndUpcaseOddLengthStrings ["here's ", "an ", "a ", "example"])

      , te "insertionSort" "  Jabcceikkqu" (insertionSort "Jack be quick")
      , te "max in a list" 100 (maxElementOfAList [3, 100, 0])
      , te "keepInBetween" [3,0,20,2] (keepInBetween 0 20 [77, 3, -1, 0, 21, 20, -9, 2])

      , te "preorder" "AB123" (concatenate (preorder show id mytree))
      , te "postrder" "12B3A" (concatenate (postorder show id mytree))
      , te "inorder" "1B2A3" (concatenate (inorder show id mytree))

      , te "eval" (BoolLit True) (eval program)
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
