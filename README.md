# Crown Vault (Android)

Crown Vault is a quantum crypto wallet and digital asset management application for Android, built with Kotlin, Jetpack Compose, and Material Design 3.

## Features

- **Vault Dashboard**: Real-time portfolio balance (`$38,190.54`), privacy balance toggle, 24h performance Bezier chart, Send & Receive transfer sheets with QR code generation and clipboard copy, and multi-asset portfolio tracking (`CRWN`, `BTC`, `ETH`, `SOL`, `USDT`).
- **Market Pulse & Active Trading**: Overview feed with the Neon Index sparkline and watchlist management, plus an Active Trading terminal supporting Market and Limit buy/sell orders across all supported assets.
- **Passive Staking**: Flexible `CRWN` staking (`14.20% APY`) with instant reward activation and Crownlink protocol protection metrics.
- **Quantum Swap**: Instant bi-directional asset exchange between `CRWN` and `ETH` with live route pricing.
- **Quantum Virtual Card**: Interactive virtual card (`••2389`) with one-tap card lock/unlock dimming, card number copy, and recent spend activity.
- **Vault+ Membership**: Upgrade flow with 7-Day Free Trial, Monthly (`$9.99/mo`) and Annual (`$89.99/yr`, Save 25%) plan selection, and instant activation.
- **System, Security & Profile**: Full multi-layer security scanner, appearance theme selector (`Midnight`, `Neon`, `Auto`), network routing selector (`Crownlink`, `Quantum Mesh`, `Direct Link`), notification alert toggles, identity profile export, and legal documentation sheets.
- **10-Language Localization & RTL Support**: Complete support for English, Español, Português, 中文, 日本語, 한국어, Deutsch, Français, العربية (with automatic RTL layout), and Русский.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose + Material 3
- **Architecture**: MVVM (`CrownVaultViewModel` + `StateFlow`)
- **Build System**: Gradle (Kotlin DSL)
