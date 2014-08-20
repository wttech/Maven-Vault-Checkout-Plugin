package com.cognifide.maven.plugins;

public aspect VaultApp {
    private Exception lastException = null;

    protected pointcut runAction(): call(public void org.apache.jackrabbit.vault.vlt.actions.Action.run(..));

    after() throwing(Exception e): runAction() {
        lastException = e;
    }

    protected pointcut runCogApplication(): call(public static void com.cognifide.maven.plugins.CogVaultFsApp.main(..));

    after(): runCogApplication() {
        if (lastException != null) {
            VltExecutionException vltExecutionException = new VltExecutionException(lastException);
            lastException = null;
            throw vltExecutionException;
        }
    }

	//org.apache.commons.cli2.commandline.Parser.parse
	// com.day.util.console.AbstractApplication.prepare
	/*
	 * protected pointcut entry() : call(* com.day.jcr.vault.cli.CmdCheckout.*(..)) || call(*
	 * com.day.util.console.AbstractApplication.*(..)) || execution(*
	 * com.day.jcr.vault.vlt.actions.Action.run(..));
	 * 
	 * after() returning(Object t): entry() { System.err.println("VaultFsApp " + t + " " + thisJoinPoint); }
	 * 
	 * after() throwing(Throwable t): entry() { System.err.println("Exception " + t + " " + thisJoinPoint); }
	 */

	/*
	 * pointcut publicCall(): call(public Object *(..)); after() returning (Object o): publicCall() {
	 * System.out.println("Returned normally with " + o + " "+ thisJoinPoint); } after() throwing (Exception
	 * e): publicCall() { System.out.println("Threw an exception: " + e + " "+ thisJoinPoint); }
	 */
	/*
	 * after(): publicCall(){ System.err.println("Returned or threw an Exception"); }
	 */
	// com.day.util.console.AbstractApplication
	/*
	 * public pointcut scope(): within(com.day.jcr.vault.cli.VaultFsApp..*);
	 * 
	 * after() throwing(Throwable t): scope() {
	 * 
	 * logThrowable(t, thisJoinPointStaticPart, thisEnclosingJoinPointStaticPart); }
	 * 
	 * before (Throwable t): handler(Exception+) && args(t) && scope() {
	 * 
	 * logThrowable(t, thisJoinPointStaticPart, thisEnclosingJoinPointStaticPart); }
	 * 
	 * protected synchronized void logThrowable(Throwable t, StaticPart location, StaticPart enclosing) {
	 * 
	 * 
	 * 
	 * Signature signature = location.getSignature();
	 * 
	 * String source = signature.getDeclaringTypeName() + ":" + (enclosing.getSourceLocation().getLine());
	 * 
	 * System.err.println("(a) " + source + " - " + t.toString());
	 * 
	 * }
	 */
}
