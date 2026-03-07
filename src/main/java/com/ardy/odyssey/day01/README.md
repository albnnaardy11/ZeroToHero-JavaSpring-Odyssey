# Day 01 — Multi-Country Tax Calculator

**Package:** `com.ardy.odyssey.day01`  
**Version:** `v2.0`

A professional-grade CLI-based tax calculator implementing advanced Java OOP patterns and data persistence.
This module is Day 1 of the Zero-to-Hero Java Spring Odyssey — covering Java OOP fundamentals:
abstract classes, interfaces, inheritance, records, and the **Factory Design Pattern**.

---

## Changelog

### v2.0 — Factory Pattern & CSV Persistence
- Implemented `TaxServiceFactory` for decoupled object creation.
- Changed audit logging from `.txt` to `.csv` for structured data processing.
- Added interactive loop (`while running`) so the program stays open for multiple entries.
- Added `InputMismatchException` handling for robust user input.
- Renamed entry point to `Main.java` (PascalCase compliance).

### v1.2 — Multi-Country Support
- Added `IndonesiTaxService` — Indonesian PPh brackets.
- Added `expense` field support in `TaxRequest` for net income calculation.
- Locale-aware currency formatting (`en_SG` / `id_ID`).

### v1.0 — Initial Release
- Singapore tax logic with abstract `TaxPayer` and `TaxCalculator` interface.

---

## What This Does

Takes annual income and expenses, calculates progressive tax based on the selected country, displays formatted results, and logs transaction data into `audit_pajak.csv`.

---

## Tax Brackets

### 🇸🇬 Singapore (SGD)

| Income Range (SGD)      | Rate   | Tax Formula                             |
|-------------------------|--------|-----------------------------------------|
| ≤ 20,000                | 0%     | SGD 0                                   |
| 20,001 – 30,000         | 2%     | (income − 20,000) × 2%                  |
| > 30,000                | 3.5%   | SGD 200 + (income − 30,000) × 3.5%     |

### 🇮🇩 Indonesia (IDR)

| Net Income Range (IDR)         | Rate  | Tax Formula                                              |
|--------------------------------|-------|----------------------------------------------------------|
| ≤ 60,000,000                   | 5%    | net × 5%                                                 |
| > 60,000,000                   | 15%   | Rp 3,000,000 + (net − 60,000,000) × 15%                 |

---

## Architecture

```
TaxCalculator (interface)
    └── calculate(TaxRequest): double

TaxPayer (abstract class)
    ├── name: String
    ├── taxId: String
    └── displayInfo(): void

TaxServiceFactory (NEW v2.0)
    └── getService(int choice, String name): TaxCalculator

SingapureTaxService & IndonesiTaxService (concrete classes)
    ├── extends TaxPayer
    ├── implements TaxCalculator
    └── calculate(TaxRequest): double

TaxRequest (record)
    ├── income: double
    └── expense: double

Main (entry point)
    ├── menu loop (Singapore | Indonesia | Exit)
    ├── uses TaxServiceFactory for object creation
    └── logs data to audit_pajak.csv
```

---

## Files

| File                     | Role                                              |
|--------------------------|---------------------------------------------------|
| `Main.java`              | Entry point — UI loop, Input validation, CSV writer|
| `TaxServiceFactory.java` | **Factory Pattern** — encapsulates object creation|
| `TaxCalculator.java`     | Interface — the contract for tax services         |
| `TaxPayer.java`          | Abstract class — shared identity properties       |
| `TaxRequest.java`        | Record — immutable input container                |
| `SingapureTaxService.java` | Concrete logic for Singapore (SGD)              |
| `IndonesiTaxService.java`  | Concrete logic for Indonesia (IDR)              |
| `audit_pajak.csv`        | Persistent audit log in CSV format                |

---

## Sample Run

```
=== Tax System v2.0 ===
1. Singapore
2. Indonesia
0. Exit
Pilih Menu: 2

Masukan Nama: Ardy
Masukan Pendapatan Tahunan: 70000000
Total Pengeluaran: 10000000

--- Hasil Perhitungan ---
Nama        : Ardy
Tax PayerArdy[ID-VET]
Total Pajak : Rp3.000.000,00
[SYSTEM]: Data tersimpan di audit_pajak.csv
```