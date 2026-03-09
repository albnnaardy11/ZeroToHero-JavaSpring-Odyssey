# Day 03 — Equipment Rental System

**Package:** `com.ardy.odyssey.day03`  
**Version:** `v1.0`

A domain-shift from the tax calculator into a real-world **camera rental service** — built to explore
Java's Collection Framework in depth. This module is Day 3 of the Zero-to-Hero Java Spring Odyssey,
covering: inheritance, encapsulation, `Queue`, `HashSet`, and explicit `Iterator` usage.

---

## Changelog

### v1.0 — Collections & OOP Foundations
- Modeled `ItemSewa` as the abstract base class for all rentable equipment.
- Extended with `Kamera` — adds `tipeSensor` field and overrides `tampilkanDetail()`.
- Implemented `LayananSewa` as the service layer, combining three Collection types:
  - `Queue<String>` (via `LinkedList`) — FIFO customer queue.
  - `HashSet<Kamera>` — duplicate-safe inventory tracking.
  - `Iterator<Kamera>` — safe in-place removal from the stock Set.

---

## What This Does

Simulates a photography equipment rental shop:
1. Cameras are registered into a `HashSet` inventory.
2. Customers are enqueued via `Queue` — first-come, first-served.
3. When a customer is served (`poll()`), they receive the next camera with a calculated rental cost.
4. When a camera is returned or retired, it is removed from inventory using `Iterator.remove()` — the
   safe, concurrent-modification-free way to delete while iterating.

---

## Architecture

```
ItemSewa (base class)
    ├── id: String
    ├── merk: String
    ├── hargaPerHari: double (private)
    ├── getId(): String
    ├── getMerk(): String
    ├── getHargaPerHari(): double
    └── tampilkanDetail(): void

Kamera extends ItemSewa
    ├── tipeSensor: String
    └── tampilkanDetail(): void  [Override — adds sensor info]

LayananSewa (service)
    ├── antrianPenyewa: Queue<String>       → LinkedList (FIFO)
    ├── daftarStok: Set<Kamera>             → HashSet (no duplicates)
    ├── tambahKeAntrian(String nama)
    ├── layananPenyewa()                    → poll() from Queue
    ├── tambahKameraKeStok(Kamera kamera)
    ├── hapusKameraDariStok(String id)      → explicit Iterator.remove()
    └── hitungTotal(ItemSewa item, int hari): double

Main (entry point)
    ├── Registers cameras to inventory (HashSet)
    ├── Enqueues customers (Queue)
    ├── Serves first customer → calculates rental cost
    └── Removes camera from stock (Iterator)
```

---

## Files

| File                       | Role                                                              |
|----------------------------|-------------------------------------------------------------------|
| `Main.java`                | Entry point — wires inventory, queue, service, and billing        |
| `model/ItemSewa.java`      | Base class — id, merk, hargaPerHari with proper encapsulation     |
| `model/Kamera.java`        | Subclass — adds `tipeSensor`, overrides `tampilkanDetail()`       |
| `service/LayananSewa.java` | Service layer — Queue + HashSet + Iterator logic                  |

---

## Key Concepts Demonstrated

| Concept           | Implementation                                      |
|-------------------|-----------------------------------------------------|
| Inheritance       | `Kamera extends ItemSewa`                           |
| Encapsulation     | `private` fields + getters only (no direct access)  |
| `@Override`       | `tampilkanDetail()` in `Kamera`                     |
| `Queue` (FIFO)    | `LinkedList` as backing impl — `add()` / `poll()`   |
| `HashSet`         | Inventory storage — prevents duplicate camera entry |
| `Iterator`        | Safe removal from `Set` during iteration            |

---

## Sample Run

```
--- Pendaftaran Antrian ---
Masuk ke dalam antrian.
Masuk ke dalam antrian.

--- Proses Sewa ---
Melayani penyewa: Ardy
[Cam-01] Merk: Sony A7IV
Sensor: Full Frame | Harga $200.0/hari
Total Biaya: $600.0

--- Update Inventaris ---
Kamera Cam-01 dihapus dari stok.
Melayani penyewa: Budi
```
