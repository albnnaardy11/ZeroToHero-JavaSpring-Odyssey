# Day 02 — Testing, Collections, and Enums

**Package:** `com.ardy.odyssey.day02`  
**Version:** `v2.1`

A professional continuation of the tax calculator system, introducing enterprise-level development practices: **Maven Build System**, **JUnit 5 Unit Testing**, **Java Enums**, and **In-Memory Collections** for state management.

---

## Changelog

### v2.1 — Enums & State Management
- Replaced Magic Numbers with `Country` Enum for strict type safety and safer mappings.
- Included `TaxHistoryManager` using `List` and `HashMap` to track user tax history and aggregate data per session.
- Upgraded the switch statement to Java Enhanced Switch (`case SINGAPORE -> ...`).

### v1.0 — Maven & JUnit 5 Integration
- Migrated out of plain Java execution to a standard Maven directory structure (`src/main/java`, `src/test/java`).
- Formulated `pom.xml` for dependency management (JUnit Jupiter, Compiler settings).
- Created `TaxCalculatorTest` containing automated assertions for both Singapore and Indonesia tax business logic.

---

## What This Does

In addition to calculating progressive taxes, Day 02 introduces a session-based **Tax History Manager**. As calculations are performed, the data is accumulated in-memory. When the process finishes, a final aggregated report of calculated taxes per user is displayed. Simultaneously, the calculation logic's integrity is guaranteed by automated JUnit tests.

---

## Architecture Additions

```
Country (enum)
    ├── SINGAPORE(1)
    ├── INDONESIA(2)
    └── fromInt(int): Country

TaxHistoryManager
    ├── history: List<Double>
    ├── summaryMap: Map<String, Double>
    ├── addRecord(String name, double amount)
    └── printLaporan()

TaxCalculatorTest (JUnit 5)
    ├── testSingaporeTaxCalculation_Higher()
    ├── testSingaporeTaxCalculation_Lower()
    ├── testIndonesiaTaxCalculation()
    └── testIndonesiaTaxCalculation_withExpense()
```

---

## Files

| File                     | Role                                                              |
|--------------------------|-------------------------------------------------------------------|
| `Country.java`           | Enum — Type-safe country representations.                         |
| `Main.java`              | Entry point — Updated CLI loop utilizing Enums and History.        |
| `TaxHistoryManager.java` | State Manager — utilizes `List` and `HashMap` to record taxes.      |
| `TaxServiceFactory.java` | Factory — Upgraded with Java Modern Switch and Enum arguments.    |
| `TaxCalculatorTest.java` | **Test Class** — Automated JUnit 5 test suite confirming logic.    |
| `pom.xml`                | Project Object Model — Maven configuration and dependencies.      |

---

## Sample Run

```
--- TAX SYSTEM v2.1 ---
1. Singapore | 2. Indonesia | 3.Keluar
Pilih: 
1
Masukan Name: 
Budi
Pendapatan: 
35000
Pajak Terhitung: 375.0

--- TAX SYSTEM v2.1 ---
1. Singapore | 2. Indonesia | 3.Keluar
Pilih: 
3

-- LAPORAN AKHIR SESI ---
Total Transaksi: 1
User: Budi | Total Pajak: 375.0
```
