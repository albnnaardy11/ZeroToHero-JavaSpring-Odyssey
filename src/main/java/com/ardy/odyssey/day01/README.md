# Day 01 — Multi-Country Tax Calculator

**Package:** `com.ardy.odyssey.day01`  
**Version:** `v1.2`

A clean, minimal CLI-based income tax calculator supporting multiple countries' progressive tax brackets.
This module is Day 1 of the Zero-to-Hero Java Spring Odyssey — covering Java OOP fundamentals:
abstract classes, interfaces, inheritance, and records. No frameworks. No magic. Just Java.

---

## Changelog

### v1.2 — Multi-Country & Audit Logging
- Added `IndonesiTaxService` — Indonesian PPh progressive tax brackets (IDR)
- Interactive country selector: Singapore or Indonesia at runtime
- `expense` field in `TaxRequest` is now live — net income = `income − expense`
- Locale-aware currency formatting (`en_SG` / `id_ID`)
- Audit log written to `audit_pajak.txt` on every calculation

### v1.1 → v1.0 — Singapore Tax Calculator
- Initial release with Singapore progressive tax logic
- Abstract `TaxPayer`, `TaxCalculator` interface, and `TaxRequest` record

---

## What This Does

Takes an annual income and optional expenses, applies the selected country's progressive tax rate,
prints the result, and appends a timestamped audit entry to `audit_pajak.txt`.
One responsibility per class. Done right.

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

> **Net income** = `income − expense`. Expenses reduce taxable base for both countries.

---

## Architecture

```
TaxCalculator (interface)
    └── calculate(TaxRequest): double

TaxPayer (abstract class)
    ├── name: String
    ├── taxId: String
    └── displayInfo(): void

SingapureTaxService (concrete class)
    ├── extends TaxPayer
    ├── implements TaxCalculator
    └── calculate(TaxRequest): double   ← Singapore progressive brackets

IndonesiTaxService (concrete class)       ← NEW in v1.2
    ├── extends TaxPayer
    ├── implements TaxCalculator
    └── calculate(TaxRequest): double   ← Indonesian PPh brackets, net income basis

TaxRequest (record)
    ├── income: double
    └── expense: double                  ← now actively used for net income calculation

main (entry point)
    ├── country selector  → 1: Singapore | 2: Indonesia
    ├── CLI loop: prompt → calculate → print
    └── audit writer      → appends entry to audit_pajak.txt
```

The design is intentional:
- `TaxCalculator` is an interface — plug in any country's tax logic with zero changes to `main`.
- `TaxPayer` is abstract — every taxpayer has a name and ID, but the tax logic differs per country.
- `SingapureTaxService` and `IndonesiTaxService` are isolated concrete implementations.
- `TaxRequest` is a record — immutable, no boilerplate, `expense` now participates in calculation.
- Audit logging is fire-and-forget, isolated in its own try-with-resources block.

---

## Sample Run

```
=== Tax System ===
Pilih Negara(1: Singapore, 2: Indonesia): 2
Masukan Nama: Ardy
Masukan Pendapatan Tahunan: 85000000
Total Pengeluaran: 5000000

--- Hasil Perhitungan ---
Nama : Ardy
Tax ID: ID-VET
Total Pajak: Rp4.500.000,00

[SYSTEM]: Data telah berhasil di backup ke audit_pajak.txt
[AUDIT]: Transaksi berhasil dicatat untuk user: Ardy
```

---

## Files

| File                     | Role                                              |
|--------------------------|---------------------------------------------------|
| `TaxCalculator.java`     | Interface — contract for all tax services         |
| `TaxPayer.java`          | Abstract class — shared taxpayer identity         |
| `TaxRequest.java`        | Record — immutable input: income + expense        |
| `SingapureTaxService.java` | Concrete — Singapore SGD tax logic              |
| `IndonesiTaxService.java`  | Concrete — Indonesia IDR PPh logic (v1.2)       |
| `main.java`              | Entry point — CLI, country selector, audit writer |
| `audit_pajak.txt`        | Append-only audit log (auto-generated at runtime) |