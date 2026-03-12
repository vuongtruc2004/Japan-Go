# ============================================
# Stage 1: Dependencies
# ============================================
FROM node:24.12.0-slim AS dependencies

WORKDIR /app

# Copy file package trước
COPY package.json package-lock.json ./
COPY .npmrc* ./

# Cài tool hay cần cho một số package native
RUN apt-get update && apt-get install -y python3 make g++ git && rm -rf /var/lib/apt/lists/*

# Ưu tiên npm ci, nếu lockfile đang lệch thì fallback npm install
RUN npm ci --no-audit --no-fund || npm install --no-audit --no-fund --legacy-peer-deps


# ============================================
# Stage 2: Build
# ============================================
FROM node:24.12.0-slim AS builder

WORKDIR /app

COPY --from=dependencies /app/node_modules ./node_modules
COPY . .

ARG NEXT_PUBLIC_API_URL
ENV NEXT_PUBLIC_API_URL=${NEXT_PUBLIC_API_URL}
ENV NODE_ENV=production

RUN npm run build


# ============================================
# Stage 3: Runner
# ============================================
FROM node:24.12.0-slim AS runner

WORKDIR /app

ENV NODE_ENV=production
ENV PORT=3000
ENV HOSTNAME=0.0.0.0

COPY --from=builder /app/public ./public
RUN mkdir -p .next

# Chỉ dùng được nếu next.config có output: 'standalone'
COPY --from=builder /app/.next/standalone ./
COPY --from=builder /app/.next/static ./.next/static

EXPOSE 3000

CMD ["node", "server.js"]