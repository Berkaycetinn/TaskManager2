# TaskManager2 Frontend Geliştirme Planı

## Teknoloji Stack

| Teknoloji | Versiyon | Amaç |
|-----------|----------|------|
| Next.js | 14.x | React framework (App Router) |
| Tailwind CSS | 3.x | Styling + Dark Mode |
| next-themes | 0.3.x | Theme switching |
| Context API | - | State management |
| Axios | 1.x | HTTP client |
| React Hook Form | 7.x | Form yönetimi |
| Zod | 3.x | Validasyon |
| Lucide React | - | Modern ikonlar |
| Framer Motion | 11.x | Animasyonlar |
| date-fns | - | Tarih işlemleri |
| react-hot-toast | - | Toast notifications |

---

## Modern Design System

### Renk Paleti

```css
/* Light Mode */
--background: #ffffff;
--foreground: #0a0a0a;
--card: #ffffff;
--primary: #6366f1;        /* Indigo */
--secondary: #f1f5f9;
--muted: #64748b;
--accent: #8b5cf6;         /* Violet */
--destructive: #ef4444;
--border: #e2e8f0;

/* Dark Mode */
--background: #0a0a0a;
--foreground: #fafafa;
--card: #171717;
--primary: #818cf8;
--secondary: #262626;
--muted: #a1a1aa;
--accent: #a78bfa;
--destructive: #f87171;
--border: #262626;
```

### Modern UI Özellikleri

1. **Glassmorphism Effects** - Backdrop blur, semi-transparent backgrounds
2. **Smooth Animations** - Page transitions, hover effects, micro-interactions
3. **Gradients** - Primary: `from-indigo-500 to-purple-600`
4. **Shadows & Depth** - Layered shadows, elevation system
5. **Typography** - Inter font, responsive sizes

---

## Proje Yapısı

```
taskmanager-frontend/
├── app/
│   ├── (auth)/
│   │   ├── login/page.tsx
│   │   ├── register/page.tsx
│   │   └── layout.tsx
│   ├── (dashboard)/
│   │   ├── layout.tsx
│   │   ├── page.tsx
│   │   └── tasks/
│   │       ├── page.tsx
│   │       ├── [id]/page.tsx
│   │       └── new/page.tsx
│   ├── layout.tsx
│   ├── globals.css
│   └── providers.tsx
├── components/
│   ├── ui/
│   ├── layout/
│   ├── tasks/
│   ├── comments/
│   ├── dashboard/
│   └── auth/
├── contexts/
├── hooks/
├── lib/
├── types/
└── middleware.ts
```

---

## Faz Detayları

### Faz 1: Proje Kurulumu
- Next.js + TypeScript + Tailwind
- Dark/Light mode (next-themes)
- CSS variables, global styles
- Axios instance, utils

### Faz 2: Authentication
- Modern login/register pages
- Split-screen design
- AuthContext, JWT management
- Route protection

### Faz 3: Layout
- Glassmorphism navbar & sidebar
- Theme toggle (animated sun/moon)
- Mobile bottom navigation
- Search modal (⌘K)

### Faz 4: Task Management
- Task cards with hover effects
- Grid/List/Kanban views
- Create/Edit modal
- Filters & search

### Faz 5: Comments & Images
- Comment section
- Image lightbox
- Drag & drop upload

### Faz 6: Dashboard
- Stat cards with animations
- Recent tasks widget
- Upcoming deadlines
- Quick add task

---

## Başlangıç Komutları

```bash
cd /Users/dogukanveziroglu/Desktop/advanced\ mid/berkay
npx create-next-app@latest taskmanager-frontend --typescript --tailwind --eslint --app
cd taskmanager-frontend
npm install axios react-hook-form @hookform/resolvers zod lucide-react date-fns framer-motion next-themes react-hot-toast clsx tailwind-merge
```
