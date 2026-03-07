# Day 01 — Singapore Tax Calculator

**Package:** `com.ardy.odyssey.day01`

A clean, minimal CLI-based income tax calculator modeled after Singapore's progressive tax brackets.
This module is Day 1 of the Zero-to-Hero Java Spring Odyssey — covering Java OOP fundamentals:
abstract classes, interfaces, inheritance, and records. No frameworks. No magic. Just Java.

---

## What This Does

Takes an annual income (SGD), applies Singapore's progressive tax rate, and prints the result.
That's it. One responsibility. Done right.

---

## Tax Brackets (Singapore, Simplified)

| Income Range (SGD)      | Rate       | Tax Formula                             |
|-------------------------|------------|-----------------------------------------|
| ≤ 20,000                | 0%         | SGD 0                                   |
| 20,001 – 30,000         | 2%         | (income − 20,000) × 2%                  |
| > 30,000                | 3.5%       | SGD 200 + (income − 30,000) × 3.5%     |

---

## Architecture

```
TaxCalculator (interface)
    └── calculate(double income): double

TaxPayer (abstract class)
    ├── name: String
    ├── taxId: String
    └── displayInfo(): void

SingapureTaxService (concrete class)
    ├── extends TaxPayer
    ├── implements TaxCalculator
    └── calculate(double income): double   ← actual tax logic lives here

TaxRequest (record)
    └── income: double
    └── expense: double                    ← reserved for future use

main (entry point)
    └── CLI loop: prompt → calculate → print
```

The design is intentional:
- `TaxCalculator` is an interface so you can plug in any country's tax logic.
- `TaxPayer` is abstract — every taxpayer has a name and ID, but the tax logic differs.
- `SingapureTaxService` is the single concrete implementation for this day.
- `TaxRequest` is a record — immutable, no boilerplate, ready for API use later.