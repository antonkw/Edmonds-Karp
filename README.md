It was a big surprise for me that there is no good public implementation of Edmonds-Karp in Scala.
By "good" I mean following points:
- immutable
- fast
- human-readable

Execution time and memory consumption is ~20% higher relatively to mutable version (mutable Maps, Arrays, etc).
Also I tried to produce easy-to-read code without non-obvious tricks to make it utilizable for learning purposes.
