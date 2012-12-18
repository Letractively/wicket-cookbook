/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.markup.transformer;

import org.apache.wicket.Component;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.protocol.http.BufferedWebResponse;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;

/**
 * A {@link Behavior} which can be added to any component. It allows to post-process (transform) the
 * markup generated by the component.
 * 
 * @see org.apache.wicket.markup.transformer.AbstractOutputTransformerContainer
 * 
 * @author Juergen Donnerstag
 */
public abstract class AbstractTransformerBehavior extends Behavior implements ITransformer
{
	private static final long serialVersionUID = 1L;

	/**
	 * The request cycle's response before the transformation.
	 */
	private transient Response originalResponse;

	/**
	 * Create a new response object which is used to store the markup generated by the child
	 * objects.
	 * 
	 * @param originalResponse
	 *            the original web response or {@code null} if it isn't a {@link WebResponse}
	 * 
	 * @return Response object. Must not be null
	 */
	protected BufferedWebResponse newResponse(final WebResponse originalResponse)
	{
		return new BufferedWebResponse(originalResponse);
	}

	@Override
	public void beforeRender(Component component)
	{
		super.beforeRender(component);

		final RequestCycle requestCycle = RequestCycle.get();

		// Temporarily replace the web response with a String response
		originalResponse = requestCycle.getResponse();

		WebResponse origResponse = (WebResponse)((originalResponse instanceof WebResponse)
			? originalResponse : null);
		BufferedWebResponse tempResponse = newResponse(origResponse);

		// temporarily set StringResponse to collect the transformed output
		requestCycle.setResponse(tempResponse);
	}

	@Override
	public void afterRender(final Component component)
	{
		final RequestCycle requestCycle = RequestCycle.get();

		try
		{
			BufferedWebResponse tempResponse = (BufferedWebResponse)requestCycle.getResponse();

			// Transform the data
			CharSequence output = transform(component, tempResponse.getText());
			originalResponse.write(output);
		}
		catch (Exception ex)
		{
			throw new WicketRuntimeException("Error while transforming the output of component: " +
				component, ex);
		}
		finally
		{
			// Restore the original response object
			requestCycle.setResponse(originalResponse);
		}
	}

	@Override
	public void detach(Component component)
	{
		originalResponse = null;
		super.detach(component);
	}

	@Override
	public abstract CharSequence transform(final Component component, final CharSequence output)
		throws Exception;
}
