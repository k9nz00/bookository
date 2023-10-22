module.exports = {
    apps: [
        {
            name: 'Web-Server 0.0.1',
            script: 'web-server.js',
            exec_mode: 'cluster',
            instances: process.env.APP_INSTANCE_COUNT || 1,
            merge_logs: true,
            restart_delay: 500,
            max_restarts: 0,
        },
    ],
};
