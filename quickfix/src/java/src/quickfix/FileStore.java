/* ====================================================================
 * The QuickFIX Software License, Version 1.0
 *
 * Copyright (c) 2001 ThoughtWorks, Inc.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        ThoughtWorks, Inc. (http://www.thoughtworks.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "QuickFIX" and "ThoughtWorks, Inc." must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact quickfix-users@lists.sourceforge.net.
 *
 * 5. Products derived from this software may not be called "QuickFIX",
 *    nor may "QuickFIX" appear in their name, without prior written
 *    permission of ThoughtWorks, Inc.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THOUGHTWORKS INC OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package quickfix;

import java.util.Collection;
import java.util.Date;
import java.io.IOException;

public class FileStore implements MessageStore {
    private int cppPointer;
    private Date creationTime;

    private FileStore() {
        create();
    }

    private FileStore(int cppPointer) {
        this.cppPointer = cppPointer;
    }

    protected void finalize() {
        destroy();
    }

    public native boolean set(int sequence, String message ) throws IOException;
    public native boolean get(int sequence, String message ) throws IOException;
    public native void get(int startSequence, int endSequence,
                    Collection messages ) throws IOException;

    public native int getNextSenderMsgSeqNum() throws IOException;
    public native int getNextTargetMsgSeqNum() throws IOException;
    public native void setNextSenderMsgSeqNum(int next) throws IOException;
    public native void setNextTargetMsgSeqNum(int next) throws IOException;
    public native void incrNextSenderMsgSeqNum() throws IOException;
    public native void incrNextTargetMsgSeqNum() throws IOException;
    public Date getCreationTime() throws IOException {
        if( creationTime == null )
	    creationTime = getCreationTime0();
        return creationTime;
    }

    public native void reset() throws IOException;

    private native void create();
    private native void destroy();

    private native Date getCreationTime0() throws IOException;
}
