import { useState, useEffect, useCallback } from 'react';
import {
  ArrowDownLeft,
  ArrowUpRight,
  BarChart3,
  Bell,
  Bitcoin,
  Check,
  ChevronRight,
  CircleDollarSign,
  CreditCard,
  Crown,
  Download,
  FileText,
  Flame,
  Gavel,
  Gift,
  Globe,
  LockKeyhole,
  Menu,
  Moon,
  MoreHorizontal,
  MoveHorizontal,
  Network,
  Plus,
  QrCode,
  ScanLine,
  Settings2,
  ShieldCheck,
  Sparkles,
  TrendingUp,
  User,
  Wallet,
  X,
  Zap,
} from 'lucide-react';
import { type Lang, type TranslationSet, type LegalDoc, translations, langList } from '@/translations';

type Screen = 'vault' | 'markets' | 'staking' | 'swap' | 'card' | 'plus' | 'system' | 'security' | 'profile';
type Plan = 'monthly' | 'annual';
type AssetIconType = 'crown' | 'btc' | 'eth' | 'sol' | 'usdt';
type Asset = { name: string; ticker: string; value: string; amount: string; change: string; tone: string; icon: AssetIconType };

const assets: Asset[] = [
  { name: 'Crown Coin', ticker: 'CRWN', value: '$18,420.00', amount: '2,400.00 CRWN', change: '+8.24%', tone: 'cyan', icon: 'crown' },
  { name: 'Bitcoin', ticker: 'BTC', value: '$67,842.50', amount: '0.1842 BTC', change: '+4.81%', tone: 'orange', icon: 'btc' },
  { name: 'Ethereum', ticker: 'ETH', value: '$3,523.18', amount: '2.745 ETH', change: '+12.40%', tone: 'purple', icon: 'eth' },
  { name: 'Solana', ticker: 'SOL', value: '$184.20', amount: '45.50 SOL', change: '+6.32%', tone: 'green', icon: 'sol' },
  { name: 'Tether', ticker: 'USDT', value: '$1.00', amount: '8,420.00 USDT', change: '+0.01%', tone: 'teal', icon: 'usdt' },
];

const navItems: { id: Screen; key: keyof TranslationSet['nav']; icon: typeof Wallet }[] = [
  { id: 'vault', key: 'vault', icon: Wallet },
  { id: 'markets', key: 'markets', icon: BarChart3 },
  { id: 'staking', key: 'staking', icon: LockKeyhole },
  { id: 'swap', key: 'swap', icon: MoveHorizontal },
  { id: 'card', key: 'card', icon: CreditCard },
  { id: 'plus', key: 'plus', icon: Sparkles },
  { id: 'system', key: 'system', icon: Settings2 },
];

function SolanaIcon({ size = 18 }: { size?: number }) {
  return (
    <svg width={size} height={size} viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M5.5 8.5L8.5 5.5C8.7 5.3 8.95 5.2 9.2 5.2H19.5C20.05 5.2 20.3 5.85 19.92 6.22L16.92 9.22C16.72 9.42 16.47 9.52 16.22 9.52H5.92C5.37 9.52 5.12 8.87 5.5 8.5Z" fill="#9945FF"/>
      <path d="M5.5 18.5L8.5 15.5C8.7 15.3 8.95 15.2 9.2 15.2H19.5C20.05 15.2 20.3 15.85 19.92 16.22L16.92 19.22C16.72 19.42 16.47 19.52 16.22 19.52H5.92C5.37 19.52 5.12 18.87 5.5 18.5Z" fill="#14F195"/>
      <path d="M16.92 11.72L13.92 14.72C13.72 14.92 13.47 15.02 13.22 15.02H2.92C2.37 15.02 2.12 14.37 2.5 14L5.5 11C5.7 10.8 5.95 10.7 6.2 10.7H16.5C17.05 10.7 17.3 11.35 16.92 11.72Z" fill="#9945FF"/>
    </svg>
  );
}

function UsdtIcon({ size = 18 }: { size?: number }) {
  return (
    <svg width={size} height={size} viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
      <circle cx="12" cy="12" r="11" fill="#26A17B"/>
      <path d="M13.5 10.2V8.5H16.5V6H7.5V8.5H10.5V10.2C8.3 10.3 6.6 10.75 6.6 11.28C6.6 11.81 8.3 12.26 10.5 12.36V17.8H13.5V12.36C15.7 12.26 17.4 11.81 17.4 11.28C17.4 10.75 15.7 10.3 13.5 10.2Z" fill="white"/>
    </svg>
  );
}

function EthIcon({ size = 18 }: { size?: number }) {
  return (
    <svg width={size} height={size} viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
      <circle cx="12" cy="12" r="11" fill="#627EEA"/>
      <path d="M12 4.5V9.8L16.5 12.1L12 4.5Z" fill="white" fillOpacity="0.6"/>
      <path d="M12 4.5L7.5 12.1L12 9.8V4.5Z" fill="white"/>
      <path d="M12 16.1V19.5L16.5 13.1L12 16.1Z" fill="white" fillOpacity="0.6"/>
      <path d="M12 19.5V16.1L7.5 13.1L12 19.5Z" fill="white"/>
      <path d="M12 15.3L16.5 13L12 10.7V15.3Z" fill="white" fillOpacity="0.2"/>
      <path d="M7.5 13L12 15.3V10.7L7.5 13Z" fill="white" fillOpacity="0.6"/>
    </svg>
  );
}

function AssetIcon({ asset, size = 18 }: { asset: Asset; size?: number }) {
  if (asset.icon === 'crown') return <span className="asset-icon crown-icon"><Crown size={size} /></span>;
  if (asset.icon === 'btc') return <span className="asset-icon btc-icon"><Bitcoin size={size} /></span>;
  if (asset.icon === 'eth') return <span className="asset-icon eth-icon"><EthIcon size={size} /></span>;
  if (asset.icon === 'sol') return <span className="asset-icon sol-icon"><SolanaIcon size={size} /></span>;
  return <span className="asset-icon usdt-icon"><UsdtIcon size={size} /></span>;
}

function BrandMark() {
  return <Crown className="brand-mark-icon" size={22} />;
}

function detectLang(): Lang {
  if (typeof navigator !== 'undefined') {
    const nav = navigator.language.toLowerCase();
    for (const l of langList) {
      if (nav.startsWith(l.code)) return l.code;
    }
  }
  return 'en';
}

function App() {
  const [screen, setScreen] = useState<Screen>('vault');
  const [modal, setModal] = useState<'send' | 'receive' | 'notify' | 'lang' | null>(null);
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [legalDoc, setLegalDoc] = useState<LegalDoc | null>(null);
  const [toast, setToast] = useState('');
  const [swapped, setSwapped] = useState(false);
  const [staked, setStaked] = useState(false);
  const [lang, setLang] = useState<Lang>(detectLang);
  const [vaultPlusOpen, setVaultPlusOpen] = useState(false);
  const [vaultPlusActive, setVaultPlusActive] = useState(false);
  const [settingsModal, setSettingsModal] = useState<'appearance' | 'network' | 'notifications' | null>(null);

  const t = translations[lang];

  const notify = useCallback((message: string) => {
    setToast(message);
    window.setTimeout(() => setToast(''), 2600);
  }, []);

  useEffect(() => {
    document.documentElement.dir = lang === 'ar' ? 'rtl' : 'ltr';
  }, [lang]);

  return (
    <main className="app-shell">
      <div className="ambient ambient-one" />
      <div className="ambient ambient-two" />
      <div className="grid-fade" />
      <div className="floating-torus floating-torus-one"><BrandMark /></div>
      <div className="floating-torus floating-torus-two"><BrandMark /></div>
      <div className="phone-frame">
        <header className="topbar">
          <button className="icon-button" onClick={() => setDrawerOpen(true)} aria-label="Open menu"><Menu size={19} /></button>
          <div className="brand"><BrandMark /><span>CROWN<span>VAULT</span></span></div>
          <button className="icon-button notification-button" onClick={() => setModal('notify')} aria-label="Notifications"><Bell size={18} /><em /></button>
        </header>

        <div className="screen-scroll">
          <div className="eyebrow-row"><span><span className="pulse-dot" /> {t.topbar.network}</span><span className="live-pill">{t.topbar.live}</span></div>
          {screen === 'vault' && <VaultView t={t} onAction={setModal} onNavigate={setScreen} />}
          {screen === 'markets' && <MarketsView t={t} onNotify={notify} />}
          {screen === 'staking' && <StakingView t={t} staked={staked} onStake={() => { setStaked(true); notify(t.toast.stakingActive); }} />}
          {screen === 'swap' && <SwapView t={t} swapped={swapped} onSwap={() => { setSwapped(!swapped); notify(t.toast.rateUpdated); }} />}
          {screen === 'card' && <CardView t={t} onNotify={notify} />}
          {screen === 'plus' && <PlusView t={t} vaultPlusActive={vaultPlusActive} onOpenModal={() => setVaultPlusOpen(true)} />}
          {screen === 'system' && <SystemView t={t} onNotify={notify} onOpenLang={() => setModal('lang')} onOpenSettings={setSettingsModal} />}
          {screen === 'security' && <SecurityView t={t} onNotify={notify} />}
          {screen === 'profile' && <ProfileView t={t} onNotify={notify} onOpenLegal={setLegalDoc} />}
        </div>

        <nav className="bottom-nav" aria-label="Main navigation">
          {navItems.map(({ id, key, icon: Icon }) => (
            <button key={id} className={`nav-item ${screen === id ? 'active' : ''}`} onClick={() => setScreen(id)}>
              <Icon size={18} strokeWidth={screen === id ? 2.2 : 1.8} /><span>{t.nav[key]}</span>
            </button>
          ))}
        </nav>
      </div>
      <Drawer t={t} open={drawerOpen} onClose={() => setDrawerOpen(false)} onNavigate={(target) => { setScreen(target); setDrawerOpen(false); }} onNotifications={() => { setModal('notify'); setDrawerOpen(false); }} onOpenLegal={(doc) => { setLegalDoc(doc); setDrawerOpen(false); }} />
      {modal && <Modal type={modal} t={t} lang={lang} onLangChange={setLang} onClose={() => setModal(null)} onNotify={notify} />}
      {legalDoc && <LegalModal doc={legalDoc} t={t} onClose={() => setLegalDoc(null)} />}
      {vaultPlusOpen && <VaultPlusModal t={t} onClose={() => setVaultPlusOpen(false)} onActivate={() => { setVaultPlusActive(true); setVaultPlusOpen(false); notify(t.toast.vaultPlusTrial); }} />}
      {settingsModal && <SettingsModal type={settingsModal} t={t} onClose={() => setSettingsModal(null)} onNotify={notify} />}
      {toast && <div className="toast"><Check size={15} />{toast}</div>}
    </main>
  );
}

function VaultView({ t, onAction, onNavigate }: { t: TranslationSet; onAction: (action: 'send' | 'receive') => void; onNavigate: (screen: Screen) => void }) {
  return <>
    <section className="hero-balance">
      <div className="balance-label">{t.vault.balanceLabel} <span className="eye">◉</span></div>
      <h1>$38,190<span>.54</span></h1>
      <div className="balance-meta"><span className="positive"><TrendingUp size={14} /> +$2,184.32 (6.06%)</span><span>24h</span></div>
      <div className="balance-chart"><svg viewBox="0 0 360 92" preserveAspectRatio="none"><defs><linearGradient id="chartFill" x1="0" x2="0" y1="0" y2="1"><stop offset="0" stopColor="#9a7bff" stopOpacity=".34" /><stop offset="1" stopColor="#9a7bff" stopOpacity="0" /></linearGradient><linearGradient id="chartLine" x1="0" x2="1"><stop stopColor="#27e8e8" /><stop offset=".5" stopColor="#b371ff" /><stop offset="1" stopColor="#ffb46b" /></linearGradient></defs><path d="M0 72 C25 66, 30 70, 48 52 S78 70, 93 58 S120 45, 132 53 S150 35, 166 43 S190 23, 202 35 S230 24, 244 29 S263 52, 278 36 S310 31, 328 15 S349 18, 360 7 V92 H0Z" fill="url(#chartFill)" /><path d="M0 72 C25 66, 30 70, 48 52 S78 70, 93 58 S120 45, 132 53 S150 35, 166 43 S190 23, 202 35 S230 24, 244 29 S263 52, 278 36 S310 31, 328 15 S349 18, 360 7" fill="none" stroke="url(#chartLine)" strokeWidth="2.5" /></svg></div>
      <div className="chart-labels"><span>00:00</span><span>06:00</span><span>12:00</span><span>18:00</span><span>{t.common.now}</span></div>
    </section>
    <div className="action-row"><button className="primary-action" onClick={() => onAction('send')}><ArrowUpRight size={18} /> {t.vault.send}</button><button className="secondary-action" onClick={() => onAction('receive')}><ArrowDownLeft size={18} /> {t.vault.receive}</button></div>
    <section className="section-block"><div className="section-heading"><div><span className="section-kicker">{t.vault.portfolio}</span><h2>{t.vault.yourAssets}</h2></div><button className="text-button">{t.vault.manage} <ChevronRight size={14} /></button></div><div className="asset-list">{assets.map((asset) => <AssetRow asset={asset} key={asset.ticker} />)}</div></section>
    <section className="quick-grid"><button className="mini-card" onClick={() => onNavigate('staking')}><span className="mini-icon mint"><LockKeyhole size={16} /></span><span><b>{t.vault.earnYield}</b><small>{t.vault.earnYieldDetail}</small></span><ChevronRight size={15} /></button><button className="mini-card" onClick={() => onNavigate('plus')}><span className="mini-icon violet"><Sparkles size={16} /></span><span><b>{t.vault.vaultPlusPerks}</b><small>{t.vault.vaultPlusPerksDetail}</small></span><ChevronRight size={15} /></button></section>
  </>;
}

function AssetRow({ asset }: { asset: Asset }) { return <button className="asset-row"><AssetIcon asset={asset} /><span className="asset-copy"><b>{asset.name}</b><small>{asset.amount}</small></span><span className="asset-value"><b>{asset.value}</b><small className="positive">{asset.change}</small></span><MoreHorizontal size={17} className="muted" /></button>; }

function MarketsView({ t, onNotify }: { t: TranslationSet; onNotify: (message: string) => void }) {
  const [tab, setTab] = useState<'overview' | 'trading'>('overview');
  const [tradeAsset, setTradeAsset] = useState(0);
  const [tradeSide, setTradeSide] = useState<'buy' | 'sell'>('buy');
  const [orderType, setOrderType] = useState<'market' | 'limit'>('market');
  const [orderAmount, setOrderAmount] = useState('');
  const asset = assets[tradeAsset];
  return <>
    <section className="page-intro"><span className="section-kicker">{t.markets.kicker}</span><h1>{t.markets.title}</h1><p>{t.markets.subtitle}</p></section>
    <div className="tab-toggle">
      <button className={`tab-btn ${tab === 'overview' ? 'active' : ''}`} onClick={() => setTab('overview')}><BarChart3 size={15} /> {t.markets.marketOverview}</button>
      <button className={`tab-btn ${tab === 'trading' ? 'active' : ''}`} onClick={() => setTab('trading')}><Zap size={15} /> {t.markets.activeTrading}</button>
    </div>
    {tab === 'overview' ? <>
      <div className="market-highlight"><div><span className="live-pill">{t.markets.topMover}</span><h2>{t.markets.neonIndex} <strong>+18.42%</strong></h2><small>{t.markets.globalSynth}</small></div><div className="sparkline"><svg viewBox="0 0 120 54"><path d="M2 43 C16 40 17 17 29 28 S45 22 53 27 S68 8 76 17 S88 31 97 15 S107 11 118 3" fill="none" stroke="#55f4dc" strokeWidth="2.5" /></svg></div></div>
      <div className="market-list">{assets.map((a, index) => <button className="market-row" key={a.ticker} onClick={() => onNotify(`${a.name} ${t.markets.addedToWatchlist}`)}><span className="rank">0{index + 1}</span><AssetIcon asset={a} /><span className="asset-copy"><b>{a.name}</b><small>{a.ticker} · {a.amount}</small></span><span className="asset-value"><b>{a.value}</b><small className="positive">{a.change}</small></span><Plus size={16} /></button>)}</div>
    </> : <>
      <div className="trade-asset-selector">{assets.map((a, i) => <button key={a.ticker} className={`trade-asset-chip ${i === tradeAsset ? 'active' : ''}`} onClick={() => setTradeAsset(i)}><AssetIcon asset={a} size={16} /><span>{a.ticker}</span></button>)}</div>
      <div className="trade-price-card">
        <div className="trade-price-header"><AssetIcon asset={asset} /><div><b>{asset.name}</b><small>{asset.ticker}</small></div></div>
        <div className="trade-price-value"><strong>{asset.value}</strong><span className="positive"><TrendingUp size={13} /> {asset.change}</span></div>
        <div className="trade-chart"><svg viewBox="0 0 320 80" preserveAspectRatio="none"><defs><linearGradient id="tradeFill" x1="0" x2="0" y1="0" y2="1"><stop offset="0" stopColor="#55f4dc" stopOpacity=".3" /><stop offset="1" stopColor="#55f4dc" stopOpacity="0" /></linearGradient></defs><path d="M0 60 C20 55, 30 62, 45 48 S70 55, 85 42 S110 50, 125 38 S150 30, 165 40 S190 22, 205 32 S230 28, 245 20 S270 35, 285 18 S305 22, 320 8 V80 H0Z" fill="url(#tradeFill)" /><path d="M0 60 C20 55, 30 62, 45 48 S70 55, 85 42 S110 50, 125 38 S150 30, 165 40 S190 22, 205 32 S230 28, 245 20 S270 35, 285 18 S305 22, 320 8" fill="none" stroke="#55f4dc" strokeWidth="2" /></svg></div>
        <div className="trade-time-labels"><span>1H</span><span>1D</span><span>1W</span><span>1M</span></div>
      </div>
      <div className="trade-side-toggle">
        <button className={`side-btn buy ${tradeSide === 'buy' ? 'active' : ''}`} onClick={() => setTradeSide('buy')}><ArrowUpRight size={15} /> {t.markets.buy}</button>
        <button className={`side-btn sell ${tradeSide === 'sell' ? 'active' : ''}`} onClick={() => setTradeSide('sell')}><ArrowDownLeft size={15} /> {t.markets.sell}</button>
      </div>
      <div className="trade-form">
        <div className="trade-form-row"><span>{t.markets.orderAmount}</span><input type="text" placeholder="0.00" value={orderAmount} onChange={(e) => setOrderAmount(e.target.value)} /></div>
        <div className="trade-form-row"><span>{t.markets.orderType}</span><div className="order-type-toggle"><button className={orderType === 'market' ? 'active' : ''} onClick={() => setOrderType('market')}>{t.markets.marketOrder}</button><button className={orderType === 'limit' ? 'active' : ''} onClick={() => setOrderType('limit')}>{t.markets.limitOrder}</button></div></div>
        <div className="trade-summary"><span>{t.swap.balance}</span><span>{asset.amount}</span></div>
      </div>
      <button className={`wide-primary trade-submit ${tradeSide === 'sell' ? 'sell' : ''}`} onClick={() => { onNotify(t.toast.orderPlaced); setOrderAmount(''); }}>{tradeSide === 'buy' ? t.markets.buy : t.markets.sell} {asset.ticker}</button>
    </>}
  </>;
}

function StakingView({ t, staked, onStake }: { t: TranslationSet; staked: boolean; onStake: () => void }) {
  return <><section className="page-intro"><span className="section-kicker">{t.staking.kicker}</span><h1>{t.staking.title}</h1><p>{t.staking.subtitle}</p></section><div className="stake-hero"><div className="stake-orb"><Flame size={27} /></div><div><span>{t.staking.totalStaked}</span><strong>{staked ? '1,200.00' : '0.00'} <small>CRWN</small></strong><em>{staked ? t.staking.earnedThisWeek : t.staking.startEarning}</em></div></div><div className="stake-grid"><div><small>{t.staking.currentApy}</small><b>14.20%</b></div><div><small>{t.staking.lockPeriod}</small><b>{t.staking.flexible}</b></div><div><small>{t.staking.networkFee}</small><b>0.01%</b></div></div><button className="wide-primary" onClick={onStake}>{staked ? <><Check size={17} /> {t.staking.stakingActive}</> : <><LockKeyhole size={17} /> {t.staking.stakeCrwn}</>}</button><section className="info-panel"><ShieldCheck size={20} /><div><b>{t.staking.protectedBy}</b><p>{t.staking.protectedDetail}</p></div></section></>;
}

function SwapView({ t, swapped, onSwap }: { t: TranslationSet; swapped: boolean; onSwap: () => void }) {
  const from = swapped ? 'ETH' : 'CRWN';
  const to = swapped ? 'CRWN' : 'ETH';
  return <><section className="page-intro"><span className="section-kicker">{t.swap.kicker}</span><h1>{t.swap.title}</h1><p>{t.swap.subtitle}</p></section><div className="swap-card"><div className="swap-label"><span>{t.swap.youPay}</span><span>{t.swap.balance}: 2,400.00 {from}</span></div><div className="swap-input"><div><AssetIcon asset={assets[swapped ? 2 : 0]} /><b>{from}</b><ChevronRight size={16} /></div><strong>{swapped ? '2.745' : '240.00'}</strong></div><button className="swap-toggle" onClick={onSwap}><MoveHorizontal size={18} /></button><div className="swap-label"><span>{t.swap.youReceive}</span><span>{t.swap.balance}: 2.745 {to}</span></div><div className="swap-input"><div><AssetIcon asset={assets[swapped ? 0 : 2]} /><b>{to}</b><ChevronRight size={16} /></div><strong>{swapped ? '2,112.00' : '0.2745'}</strong></div><div className="swap-rate"><span>1 {from} = {swapped ? '875.3' : '0.00114'} {to}</span><span className="positive">{t.swap.bestRoute}</span></div></div><button className="wide-primary" onClick={onSwap}><Zap size={17} /> {t.swap.reviewSwap}</button><div className="powered"><span>{t.swap.poweredBy}</span><b><Crown size={13} /> {t.swap.router}</b></div></>;
}

function CardView({ t, onNotify }: { t: TranslationSet; onNotify: (message: string) => void }) {
  const [locked, setLocked] = useState(false);
  return <><section className="page-intro"><span className="section-kicker">{t.card.kicker}</span><h1>{t.card.title}</h1><p>{t.card.subtitle}</p></section><div className={`quantum-card ${locked ? 'card-dimmed' : ''}`}><div className="card-top"><span className="brand-mini"><Crown size={14} /> CROWN</span><span>∞ / ∞</span></div><div className="card-chip"><span /><span /><span /></div><strong>•••• &nbsp; •••• &nbsp; •••• &nbsp; 2389</strong><div className="card-bottom"><span>AEON VAULT</span><span>09/29</span><span className="master-mark">◉◉</span></div></div><div className="card-status"><span className="status-dot" /><div><b>{t.card.cardReady}</b><small>{t.card.virtualCard}</small></div><ChevronRight size={17} /></div><div className="card-actions"><button onClick={() => onNotify(t.toast.cardDetailsCopied)}><QrCode size={17} /> {t.card.details}</button><button onClick={() => { setLocked(!locked); onNotify(locked ? t.toast.cardUnlocked : t.toast.cardLocked); }}>{locked ? <><LockKeyhole size={17} /> {t.card.unlockCard}</> : <><LockKeyhole size={17} /> {t.card.lockCard}</>}</button></div><section className="section-block"><div className="section-heading"><div><span className="section-kicker">{t.card.recentSpend}</span><h2>{t.card.activity}</h2></div><button className="text-button">{t.card.seeAll} <ChevronRight size={14} /></button></div><div className="activity-list"><div><span className="activity-icon"><Download size={15} /></span><b>{t.card.orbitalMarket}</b><small>{t.card.today}</small><strong>−$42.80</strong></div><div><span className="activity-icon"><CircleDollarSign size={15} /></span><b>{t.card.cashbackReward}</b><small>{t.card.yesterday}</small><strong className="positive">+$4.28</strong></div></div></section></>;
}

function PlusView({ t, vaultPlusActive, onOpenModal }: { t: TranslationSet; vaultPlusActive: boolean; onOpenModal: () => void }) {
  return (
    <>
      <div className="plus-banner">
        <div className="plus-symbol"><Sparkles size={26} /></div>
        <span className="section-kicker">{t.plus.kicker}</span>
        <h1>{t.plus.title}<span>+</span></h1>
        <p>{t.plus.subtitle}</p>
        <p className="freemium-note">{t.vaultPlus.freemiumMessage}</p>
        {vaultPlusActive ? (
          <div className="vaultplus-active-badge"><ShieldCheck size={15} /> {t.vaultPlus.statusActive}</div>
        ) : (
          <button className="light-button" onClick={onOpenModal}>{t.plus.startFreeTrial} <ArrowUpRight size={15} /></button>
        )}
      </div>
      <div className="benefit-list">
        <Benefit icon={<Zap size={17} />} title={t.plus.instantSettlement} detail={t.plus.instantSettlementDetail} />
        <Benefit icon={<Gift size={17} />} title={t.plus.doubleRewards} detail={t.plus.doubleRewardsDetail} />
        <Benefit icon={<Network size={17} />} title={t.plus.priorityRouting} detail={t.plus.priorityRoutingDetail} />
      </div>
      <div className="member-note"><Crown size={19} /><span><b>{t.plus.curated}</b><small>{t.plus.joinMembers}</small></span><ChevronRight size={16} /></div>
      <div className="zk-badge"><ShieldCheck size={14} /> {t.common.zeroKnowledgeBadge}</div>
    </>
  );
}

function VaultPlusModal({ t, onClose, onActivate }: { t: TranslationSet; onClose: () => void; onActivate: () => void }) {
  const [plan, setPlan] = useState<Plan>('monthly');
  const features = [
    { icon: <Zap size={18} />, title: t.vaultPlus.featureInstantTitle, detail: t.vaultPlus.featureInstantDetail },
    { icon: <Gift size={18} />, title: t.vaultPlus.featureStakingTitle, detail: t.vaultPlus.featureStakingDetail },
    { icon: <Network size={18} />, title: t.vaultPlus.featurePriorityTitle, detail: t.vaultPlus.featurePriorityDetail },
    { icon: <ShieldCheck size={18} />, title: t.vaultPlus.featureShieldTitle, detail: t.vaultPlus.featureShieldDetail },
    { icon: <CreditCard size={18} />, title: t.vaultPlus.featureCardTitle, detail: t.vaultPlus.featureCardDetail },
  ];
  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div className="modal-sheet vaultplus-sheet" onClick={(e) => e.stopPropagation()}>
        <div className="modal-heading">
          <div>
            <span className="section-kicker">VAULT+</span>
            <h2>{t.vaultPlus.header}</h2>
          </div>
          <button className="icon-button" onClick={onClose} aria-label="Close"><X size={18} /></button>
        </div>
        <div className="vaultplus-badge"><Sparkles size={14} /> {t.vaultPlus.badge}</div>
        <div className="plan-toggle">
          <button className={`plan-option ${plan === 'monthly' ? 'active' : ''}`} onClick={() => setPlan('monthly')}>
            <span className="plan-label">{t.vaultPlus.monthly}</span>
            <span className="plan-price">$9.99<span className="plan-unit">{t.vaultPlus.perMonth}</span></span>
          </button>
          <button className={`plan-option ${plan === 'annual' ? 'active' : ''}`} onClick={() => setPlan('annual')}>
            <span className="plan-label">{t.vaultPlus.annual}</span>
            <span className="plan-price">$89.99<span className="plan-unit">{t.vaultPlus.perYear}</span></span>
            <span className="plan-save">{t.vaultPlus.save25}</span>
          </button>
        </div>
        <div className="vaultplus-features">
          {features.map((f, i) => (
            <div className="vaultplus-feature" key={i}>
              <span className="vaultplus-feature-icon">{f.icon}</span>
              <div><b>{f.title}</b><small>{f.detail}</small></div>
              <Check size={16} className="vaultplus-check" />
            </div>
          ))}
        </div>
        <p className="vaultplus-free-tier-note">{t.vaultPlus.freeTierNote}</p>
        <button className="wide-primary vaultplus-cta" onClick={onActivate}>
          <Sparkles size={17} /> {t.vaultPlus.ctaButton}
        </button>
        <p className="vaultplus-footer">{t.vaultPlus.footer}</p>
      </div>
    </div>
  );
}
function Benefit({ icon, title, detail }: { icon: React.ReactNode; title: string; detail: string }) { return <div className="benefit"><span>{icon}</span><div><b>{title}</b><small>{detail}</small></div><Check size={16} /></div>; }

function SystemView({ t, onNotify, onOpenLang, onOpenSettings }: { t: TranslationSet; onNotify: (message: string) => void; onOpenLang: () => void; onOpenSettings: (type: 'appearance' | 'network' | 'notifications') => void }) {
  const [scanning, setScanning] = useState(false);
  const [scanned, setScanned] = useState(false);
  const handleScan = () => {
    if (scanning) return;
    setScanning(true);
    window.setTimeout(() => { setScanning(false); setScanned(true); onNotify(t.toast.scanComplete); }, 2000);
  };
  return <><section className="page-intro"><span className="section-kicker">{t.system.kicker}</span><h1>{t.system.title}</h1><p>{t.system.subtitle}</p></section><div className="system-profile"><div className="avatar"><Crown size={22} /></div><div><b>Traveler 07</b><small>0x7A...C89F</small></div><span className="verified"><ShieldCheck size={13} /> {t.common.verified}</span></div><div className="settings-list"><Setting icon={<ShieldCheck size={17} />} title={t.system.securityCenter} detail={scanned ? t.common.verified : t.system.allSystemsNominal} accent="green" /><Setting icon={<Moon size={17} />} title={t.system.appearance} detail={t.system.appearanceDetail} onClick={() => onOpenSettings('appearance')} /><Setting icon={<Network size={17} />} title={t.system.networkPref} detail={t.system.networkDetail} onClick={() => onOpenSettings('network')} /><Setting icon={<Globe size={17} />} title={t.system.language} detail={t.system.languageDetail} onClick={onOpenLang} /><Setting icon={<Bell size={17} />} title={t.system.notifications} detail={t.system.notificationsDetail} onClick={() => onOpenSettings('notifications')} /></div><button className="wide-outline" onClick={handleScan}>{scanning ? <><span className="scan-spinner" /> {t.system.scanning}</> : <><ScanLine size={17} /> {t.system.runSecurityScan}</>}</button><div className="zk-badge"><ShieldCheck size={14} /> {t.common.zeroKnowledgeBadge}</div><div className="version">{t.system.osVersion} <span>v2.389.04</span></div></>;
}
function Setting({ icon, title, detail, accent, onClick }: { icon: React.ReactNode; title: string; detail: string; accent?: string; onClick?: () => void }) { return <button className="setting-row" onClick={onClick}><span className={`setting-icon ${accent ?? ''}`}>{icon}</span><span><b>{title}</b><small>{detail}</small></span><ChevronRight size={16} /></button>; }

function SecurityView({ t, onNotify }: { t: TranslationSet; onNotify: (message: string) => void }) {
  const [scanning, setScanning] = useState(false);
  const [scanned, setScanned] = useState(false);
  const handleScan = () => {
    if (scanning) return;
    setScanning(true);
    window.setTimeout(() => { setScanning(false); setScanned(true); onNotify(t.toast.scanComplete); }, 2000);
  };
  return <><section className="page-intro"><span className="section-kicker">{t.security.kicker}</span><h1>{t.security.title}</h1><p>{t.security.subtitle}</p></section><div className={`security-shield ${scanning ? 'scanning' : ''} ${scanned ? 'scanned' : ''}`}><ShieldCheck size={48} /></div><div className="settings-list"><Setting icon={<ShieldCheck size={17} />} title={t.security.biometricLock} detail={t.security.biometricDetail} accent="green" /><Setting icon={<LockKeyhole size={17} />} title={t.security.twoFactor} detail={t.security.twoFactorDetail} accent="green" /><Setting icon={<ScanLine size={17} />} title={t.security.txScanning} detail={t.security.txScanningDetail} accent="green" /><Setting icon={<Network size={17} />} title={t.security.whitelisted} detail={t.security.whitelistedDetail} /></div><button className="wide-primary" onClick={handleScan} disabled={scanning}>{scanning ? <><span className="scan-spinner" /> {t.security.scanning}</> : <><ScanLine size={17} /> {t.security.runFullScan}</>}</button>{scanning && <div className="scan-progress-panel"><div className="scan-line-anim" /><span>{t.security.scanningLayers}</span></div>}{scanned && <section className="info-panel scan-success"><ShieldCheck size={20} /><div><b>{t.security.systemsNominal}</b><p>{t.security.zeroThreats}</p></div></section>}{!scanned && !scanning && <section className="info-panel"><ShieldCheck size={20} /><div><b>{t.security.allNominal}</b><p>{t.security.lastScan}</p></div></section>}</>;
}

function ProfileView({ t, onNotify, onOpenLegal }: { t: TranslationSet; onNotify: (message: string) => void; onOpenLegal: (doc: LegalDoc) => void }) {
  return <><section className="page-intro"><span className="section-kicker">{t.profile.kicker}</span><h1>{t.profile.title}</h1><p>{t.profile.subtitle}</p></section><div className="system-profile"><div className="avatar"><Crown size={22} /></div><div><b>Traveler 07</b><small>0x7A91...C89F</small></div><span className="verified"><ShieldCheck size={13} /> {t.common.verified}</span></div><div className="settings-list"><Setting icon={<User size={17} />} title={t.profile.displayName} detail="Traveler 07" /><Setting icon={<ShieldCheck size={17} />} title={t.profile.verificationStatus} detail={t.profile.verifiedTier3} accent="green" /><Setting icon={<Network size={17} />} title={t.profile.walletAddress} detail="0x7A91...C89F" /></div><div className="drawer-legal-section profile-legal"><span className="drawer-legal-title"><Gavel size={14} /> {t.drawer.legal}</span><button className="drawer-legal-item" onClick={() => onOpenLegal('terms')}><span className="drawer-legal-icon"><FileText size={16} /></span><span>{t.legal.terms.title}</span><ChevronRight size={14} className="drawer-chevron" /></button><button className="drawer-legal-item" onClick={() => onOpenLegal('privacy')}><span className="drawer-legal-icon"><FileText size={16} /></span><span>{t.legal.privacy.title}</span><ChevronRight size={14} className="drawer-chevron" /></button><button className="drawer-legal-item" onClick={() => onOpenLegal('usage')}><span className="drawer-legal-icon"><FileText size={16} /></span><span>{t.legal.usage.title}</span><ChevronRight size={14} className="drawer-chevron" /></button><button className="drawer-legal-item" onClick={() => onOpenLegal('disclaimer')}><span className="drawer-legal-icon"><FileText size={16} /></span><span>{t.legal.disclaimer.title}</span><ChevronRight size={14} className="drawer-chevron" /></button></div><button className="wide-outline" onClick={() => onNotify(t.toast.profileExported)}><Download size={17} /> {t.profile.exportData}</button></>;
}

function Modal({ type, t, lang, onLangChange, onClose, onNotify }: { type: 'send' | 'receive' | 'notify' | 'lang'; t: TranslationSet; lang: Lang; onLangChange: (l: Lang) => void; onClose: () => void; onNotify: (message: string) => void }) {
  const isNotify = type === 'notify';
  const isLang = type === 'lang';
  return <div className="modal-backdrop" onClick={onClose}><div className="modal-sheet" onClick={(event) => event.stopPropagation()}><div className="modal-heading"><div><span className="section-kicker">{isLang ? 'LANGUAGE' : isNotify ? t.modal.inbox : type === 'send' ? t.modal.transferOut : t.modal.transferIn}</span><h2>{isLang ? t.system.language : isNotify ? t.modal.notifications : type === 'send' ? t.modal.sendAssets : t.modal.receiveAssets}</h2></div><button className="icon-button" onClick={onClose}><X size={18} /></button></div>{isLang ? <div className="lang-list">{langList.map((l) => <button key={l.code} className={`lang-item ${lang === l.code ? 'active' : ''}`} onClick={() => { onLangChange(l.code); onClose(); }}><span className="lang-native">{l.native}</span><span className="lang-code">{l.code.toUpperCase()}</span>{lang === l.code && <Check size={16} className="lang-check" />}</button>)}</div> : isNotify ? <div className="notification-empty"><div><Bell size={22} /></div><b>{t.modal.allCaughtUp}</b><p>{t.modal.newActivity}</p></div> : <><div className="qr-placeholder">{type === 'receive' ? <QrCode size={104} strokeWidth={1.2} /> : <ArrowUpRight size={34} />}</div><div className="address-box">{type === 'receive' ? '0x7A91...C89F' : t.modal.pasteAddress}<CopyButton /></div><button className="wide-primary" onClick={() => { onClose(); onNotify(type === 'send' ? t.modal.transferPrepared : t.modal.addressCopied); }}>{type === 'send' ? t.modal.continue : t.modal.copyAddress}</button></>}</div></div>;
}
function CopyButton() { return <button className="copy-button" aria-label="Copy"><ScanLine size={14} /></button>; }

function Drawer({ t, open, onClose, onNavigate, onNotifications, onOpenLegal }: { t: TranslationSet; open: boolean; onClose: () => void; onNavigate: (screen: Screen) => void; onNotifications: () => void; onOpenLegal: (doc: LegalDoc) => void }) {
  const items: { icon: React.ReactNode; label: string; detail: string; onClick: () => void }[] = [
    { icon: <Settings2 size={18} />, label: t.drawer.settings, detail: t.drawer.settingsDetail, onClick: () => onNavigate('system') },
    { icon: <ShieldCheck size={18} />, label: t.drawer.security, detail: t.drawer.securityDetail, onClick: () => onNavigate('security') },
    { icon: <Bell size={18} />, label: t.drawer.notifications, detail: t.drawer.notificationsDetail, onClick: onNotifications },
    { icon: <User size={18} />, label: t.drawer.profile, detail: t.drawer.profileDetail, onClick: () => onNavigate('profile') },
  ];
  const legalItems: { icon: React.ReactNode; label: string; doc: LegalDoc }[] = [
    { icon: <FileText size={16} />, label: t.legal.terms.title, doc: 'terms' },
    { icon: <FileText size={16} />, label: t.legal.privacy.title, doc: 'privacy' },
    { icon: <FileText size={16} />, label: t.legal.usage.title, doc: 'usage' },
    { icon: <FileText size={16} />, label: t.legal.disclaimer.title, doc: 'disclaimer' },
  ];
  return (
    <div className={`drawer-backdrop ${open ? 'open' : ''}`} onClick={onClose} aria-hidden={!open}>
      <aside className={`drawer ${open ? 'open' : ''}`} onClick={(event) => event.stopPropagation()}>
        <header className="drawer-header">
          <div className="drawer-profile">
            <div className="drawer-avatar"><Crown size={22} /></div>
            <div><b>Traveler 07</b><small>0x7A91...C89F</small></div>
          </div>
          <button className="icon-button" onClick={onClose} aria-label="Close menu"><X size={18} /></button>
        </header>
        <div className="drawer-balance">
          <span className="section-kicker">{t.drawer.vaultBalance}</span>
          <strong>$38,190.54</strong>
          <em className="positive">+$2,184.32 · 6.06%</em>
        </div>
        <nav className="drawer-nav">
          {items.map((item) => (
            <button key={item.label} className="drawer-item" onClick={item.onClick}>
              <span className="drawer-item-icon">{item.icon}</span>
              <span className="drawer-item-copy"><b>{item.label}</b><small>{item.detail}</small></span>
              <ChevronRight size={16} className="drawer-chevron" />
            </button>
          ))}
        </nav>
        <div className="drawer-legal-section">
          <span className="drawer-legal-title"><Gavel size={14} /> {t.drawer.legal}</span>
          {legalItems.map((item) => (
            <button key={item.label} className="drawer-legal-item" onClick={() => onOpenLegal(item.doc)}>
              <span className="drawer-legal-icon">{item.icon}</span>
              <span>{item.label}</span>
              <ChevronRight size={14} className="drawer-chevron" />
            </button>
          ))}
        </div>
        <div className="drawer-footer">
          <div className="drawer-status"><span className="pulse-dot" /> Crownlink · 12 ms</div>
          <span className="version">v2.389.04</span>
        </div>
        <div className="drawer-signature">
          Crown Vault was developed and designed by<br />Crown Tech&#8482;. All rights reserved &#174; 2026
        </div>
      </aside>
    </div>
  );
}

function LegalModal({ doc, t, onClose }: { doc: LegalDoc; t: TranslationSet; onClose: () => void }) {
  const content = t.legal[doc];
  return (
    <div className="modal-backdrop legal-backdrop" onClick={onClose}>
      <div className="modal-sheet legal-sheet" onClick={(event) => event.stopPropagation()}>
        <div className="modal-heading">
          <div><span className="section-kicker">LEGAL</span><h2>{content.title}</h2></div>
          <button className="icon-button" onClick={onClose} aria-label="Close"><X size={18} /></button>
        </div>
        <div className="legal-body">{content.paragraphs.map((p: string, i: number) => <p key={i}>{p}</p>)}</div>
        <div className="legal-signature">Crown Tech&#8482; &#174; 2026</div>
      </div>
    </div>
  );
}

function SettingsModal({ type, t, onClose, onNotify }: { type: 'appearance' | 'network' | 'notifications'; t: TranslationSet; onClose: () => void; onNotify: (message: string) => void }) {
  const [theme, setTheme] = useState('midnight');
  const [network, setNetwork] = useState('crownlink');
  const [notifSmart, setNotifSmart] = useState(true);
  const [notifPrice, setNotifPrice] = useState(true);
  const [notifSecurity, setNotifSecurity] = useState(true);
  const title = type === 'appearance' ? t.system.appearanceTitle : type === 'network' ? t.system.networkTitle : t.system.notificationsTitle;
  const handleSelect = (msg: string) => { onNotify(msg); onClose(); };
  return (
    <div className="modal-backdrop" onClick={onClose}>
      <div className="modal-sheet" onClick={(e) => e.stopPropagation()}>
        <div className="modal-heading">
          <div><span className="section-kicker">SETTINGS</span><h2>{title}</h2></div>
          <button className="icon-button" onClick={onClose} aria-label="Close"><X size={18} /></button>
        </div>
        {type === 'appearance' && <div className="settings-modal-body">
          <button className={`select-option ${theme === 'midnight' ? 'active' : ''}`} onClick={() => { setTheme('midnight'); handleSelect(t.system.themeApplied); }}><Moon size={18} /><span>{t.system.themeMidnight}</span>{theme === 'midnight' && <Check size={16} className="select-check" />}</button>
          <button className={`select-option ${theme === 'neon' ? 'active' : ''}`} onClick={() => { setTheme('neon'); handleSelect(t.system.themeApplied); }}><Sparkles size={18} /><span>{t.system.themeNeon}</span>{theme === 'neon' && <Check size={16} className="select-check" />}</button>
          <button className={`select-option ${theme === 'auto' ? 'active' : ''}`} onClick={() => { setTheme('auto'); handleSelect(t.system.themeApplied); }}><Settings2 size={18} /><span>{t.system.themeAuto}</span>{theme === 'auto' && <Check size={16} className="select-check" />}</button>
        </div>}
        {type === 'network' && <div className="settings-modal-body">
          <button className={`select-option ${network === 'crownlink' ? 'active' : ''}`} onClick={() => { setNetwork('crownlink'); handleSelect(t.system.networkSwitched); }}><Network size={18} /><span>{t.system.netCrownlink}</span>{network === 'crownlink' && <Check size={16} className="select-check" />}</button>
          <button className={`select-option ${network === 'quantum' ? 'active' : ''}`} onClick={() => { setNetwork('quantum'); handleSelect(t.system.networkSwitched); }}><Network size={18} /><span>{t.system.netQuantumMesh}</span>{network === 'quantum' && <Check size={16} className="select-check" />}</button>
          <button className={`select-option ${network === 'direct' ? 'active' : ''}`} onClick={() => { setNetwork('direct'); handleSelect(t.system.networkSwitched); }}><Network size={18} /><span>{t.system.netDirectLink}</span>{network === 'direct' && <Check size={16} className="select-check" />}</button>
        </div>}
        {type === 'notifications' && <div className="settings-modal-body">
          <div className="toggle-row"><div><b>{t.system.notifSmartAlerts}</b><small>{t.system.notifSmartAlertsDetail}</small></div><button className={`toggle-switch ${notifSmart ? 'on' : ''}`} onClick={() => setNotifSmart(!notifSmart)}><span /></button></div>
          <div className="toggle-row"><div><b>{t.system.notifPriceAlerts}</b><small>{t.system.notifPriceAlertsDetail}</small></div><button className={`toggle-switch ${notifPrice ? 'on' : ''}`} onClick={() => setNotifPrice(!notifPrice)}><span /></button></div>
          <div className="toggle-row"><div><b>{t.system.notifSecurityAlerts}</b><small>{t.system.notifSecurityAlertsDetail}</small></div><button className={`toggle-switch ${notifSecurity ? 'on' : ''}`} onClick={() => setNotifSecurity(!notifSecurity)}><span /></button></div>
          <div className="notif-push-badge"><Check size={14} /> {t.system.notifPushEnabled}</div>
          <button className="wide-primary" onClick={() => handleSelect(t.system.notifSettingsSaved)}><Check size={17} /> {t.system.notifSettingsSaved}</button>
        </div>}
      </div>
    </div>
  );
}

export default App;
