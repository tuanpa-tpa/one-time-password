1. Backend server creates a secret key for that particular user.
2. Server then shares that secret key with the user’s phone application.
3. Phone application initializes a counter.
4. Phone application generate a one time password using that secret key and counter.
5. Phone application changes the counter after a certain interval and regenerates the one time password making it dynamic.
