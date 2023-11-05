// eslint-disable-next-line no-undef
module.exports = {
  apps: [
    {
      name: 'web-Server 0.0.1',
      script: 'web-server.js',
      exec_mode: 'cluster',
      // eslint-disable-next-line no-undef
      instances: 1,
      merge_logs: true,
      restart_delay: 500,
      max_restarts: 0
    }
  ]
}
