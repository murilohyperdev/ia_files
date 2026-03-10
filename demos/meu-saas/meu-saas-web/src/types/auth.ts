export interface User {
  id: string
  merchantId: string
  name: string
  email: string
  avatarUrl?: string
  phone?: string
  isActive: boolean
  emailVerifiedAt?: string
  lastLoginAt?: string
  createdAt: string
  groups: Group[]
  rules: string[]
}

export interface Group {
  id: string
  name: string
  description?: string
}

export interface LoginRequest {
  email: string
  password: string
}

export interface RegisterRequest {
  name: string
  email: string
  password: string
  confirmPassword: string
}

export interface TokenResponse {
  accessToken: string
  refreshToken: string
  expiresIn: number
  user: User
}

export interface RefreshTokenRequest {
  refreshToken: string
}
