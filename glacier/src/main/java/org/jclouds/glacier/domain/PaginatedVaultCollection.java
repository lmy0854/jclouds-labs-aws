/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.glacier.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;
import java.util.Iterator;

import org.jclouds.collect.IterableWithMarker;
import org.jclouds.glacier.options.PaginationOptions;

import com.google.common.base.Optional;
import com.google.gson.annotations.SerializedName;

/**
 * Paginated collection used to store Vault listing information.
 */
public class PaginatedVaultCollection extends IterableWithMarker<VaultMetadata> {

   @SerializedName("VaultList")
   private final Iterable<VaultMetadata> vaults;
   @SerializedName("Marker")
   private final String marker;

   @ConstructorProperties({ "VaultList", "Marker" })
   public PaginatedVaultCollection(Iterable<VaultMetadata> vaults, String marker) {
      this.vaults = checkNotNull(vaults, "vaults");
      this.marker = marker;
   }

   @Override
   public Iterator<VaultMetadata> iterator() {
      return vaults.iterator();
   }

   @Override
   public Optional<Object> nextMarker() {
      return Optional.<Object>fromNullable(marker);
   }

   public PaginationOptions nextPaginationOptions() {
      return PaginationOptions.class.cast(nextMarker().get());
   }
}
