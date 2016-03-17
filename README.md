# obx
Functional prefix programming language for golf or math or anything you want.

## Short Explanation
Each line is a function, and the last line is executed with the inputs.
Each function takes up to 3 inputs (`x`, `y`, and `z`), and returns the result.
Functions are written in prefix notation, like Pyth.

```
+xy
A1x
```

With an input of `5`, this program would output `6`. Let's learn why.

`+xy` creates a function called _A_ that adds _x_ (the first argument) and _y_ (the second argument).
`A1x` creates a function called _B_ that calls _A_ with `1` and _x_ (the first argument).
The last function created is called with whatever the input is.