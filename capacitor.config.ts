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
      serverClientId: 'WEB_CLIENT_ID_KAMU.apps.googleusercontent.com',
      forceCodeForRefreshToken: true
    }
  }
};

export default config;
