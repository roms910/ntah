import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.romzz.music',
  appName: 'Monochrome',
  webDir: 'dist',
  server: {
    url: 'https://mono.romzz.biz.id',
    cleartext: false
  },
  plugins: {
    GoogleAuth: {
      scopes: ['profile', 'email'],
      serverClientId: '707309494548-m4bshr784jpobmrc73ejtmdkja54bpt3.apps.googleusercontent.com',
      forceCodeForRefreshToken: true
    }
  }
};

export default config;
